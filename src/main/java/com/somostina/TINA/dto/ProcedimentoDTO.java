
package com.somostina.TINA.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.somostina.TINA.domain.Category;
import com.somostina.TINA.domain.Procedimento;

public class ProcedimentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProcedimentoDTO() {
	}

	public ProcedimentoDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ProcedimentoDTO(Procedimento entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public ProcedimentoDTO(Procedimento entity, Set<Category> categories) {
		this.id = entity.getId();
		this.name = entity.getName();
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
}
