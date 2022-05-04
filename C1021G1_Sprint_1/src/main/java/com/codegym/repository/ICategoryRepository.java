package com.codegym.repository;

import com.codegym.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select id, `name` from category where id =?", nativeQuery = true)
    Category findCategoryById(Long id);
}
