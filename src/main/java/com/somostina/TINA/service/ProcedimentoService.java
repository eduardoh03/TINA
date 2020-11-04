package com.somostina.TINA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somostina.TINA.domain.Category;
import com.somostina.TINA.domain.Procedimento;
import com.somostina.TINA.dto.CategoryDTO;
import com.somostina.TINA.dto.ProcedimentoDTO;
import com.somostina.TINA.exceptions.DatabaseException;
import com.somostina.TINA.exceptions.ResourceNotFoundException;
import com.somostina.TINA.repositories.CategoryRepository;
import com.somostina.TINA.repositories.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	
	@Autowired
	private ProcedimentoRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public List<ProcedimentoDTO> findAll() {
		List<Procedimento> list = repository.findAll();
		return list.stream().map(x -> new ProcedimentoDTO(x, x.getCategories()))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<ProcedimentoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Procedimento> list = repository.findAll(pageRequest);
		return list.map(x -> new ProcedimentoDTO(x));
	}

	@Transactional(readOnly = true)
	public ProcedimentoDTO findById(Long id) {
		Optional<Procedimento> obj = repository.findById(id);
		Procedimento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));
		return new ProcedimentoDTO(entity);
	}

	@Transactional
	public ProcedimentoDTO insert(ProcedimentoDTO dto) {
		Procedimento entity = new Procedimento();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProcedimentoDTO(entity,entity.getCategories());
	}

	@Transactional
	public ProcedimentoDTO update(Long id, ProcedimentoDTO dto) {
		try {
			Procedimento entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProcedimentoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			String error = String.format("ID %s não encontrado", id);
			throw new ResourceNotFoundException(error);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	private void copyDtoToEntity(ProcedimentoDTO dto, Procedimento entity) {
		entity.setId(dto.getId());;
		entity.setName(dto.getName());;
		
		entity.getCategories().clear();
		for (CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}
	
}
