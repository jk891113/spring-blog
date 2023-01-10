package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> getAllByOrderByNameAsc();
    List<Category> getCategoriesByLayer(int layer);
    Optional<Category> findByName(String name);
    Optional<Category> findByCategoryId(Long categoryId);
    List<Category> getCategoriesByParent(String parent);
    void deleteByParent(String parent);
}
