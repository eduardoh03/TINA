package com.somostina.TINA.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.somostina.TINA.dto.ProcedimentoDTO;
import com.somostina.TINA.service.ProcedimentoService;

@RestController
@RequestMapping(value= "/procedimentos")
public class ProcedimentoResources {
	
	@Autowired
	private ProcedimentoService service;
	
	@GetMapping
	public ResponseEntity<List<ProcedimentoDTO>> findAll(){
		List<ProcedimentoDTO> list = service.findAll();
	return ResponseEntity.ok().body(list);
	}
	@GetMapping(value= "/paged")
	public ResponseEntity<Page<ProcedimentoDTO>> findAll(
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
		@RequestParam(value = "direction", defaultValue = "ASC") String direction,
		@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<ProcedimentoDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProcedimentoDTO> findById(@PathVariable Long id) {
		ProcedimentoDTO list = service.findById(id);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<ProcedimentoDTO> insert(@RequestBody ProcedimentoDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProcedimentoDTO> update(@PathVariable Long id, @RequestBody ProcedimentoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProcedimentoDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
