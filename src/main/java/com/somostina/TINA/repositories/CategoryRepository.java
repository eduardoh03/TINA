package com.somostina.TINA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somostina.TINA.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
