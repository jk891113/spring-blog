package com.sparta.springblog.service;

import com.sparta.springblog.entity.Category;
import com.sparta.springblog.repository.CategoryRepository;
import com.sparta.springblog.responsedto.CategoryListResponseDto;
import com.sparta.springblog.responsedto.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryListResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.getAllByOrderByNameAsc();
        List<Category> parentCategories = categoryRepository.getCategoriesByLayer(0);
        List<CategoryListResponseDto> responseDtos = parentCategories.stream()
                .map(category -> new CategoryListResponseDto(category, categories))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public List<CategoryResponseDto> getMainCategories() {
        List<Category> categories = categoryRepository.getCategoriesByLayer(0);
        List<CategoryResponseDto> responseDtos = categories.stream()
                .map(category -> new CategoryResponseDto(category))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public List<CategoryResponseDto> getChildrenCategories(Long categoryId) {
        Category parentCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        List<Category> childrenCategories = categoryRepository.getCategoriesByParent(parentCategory.getName());
        List<CategoryResponseDto> responseDtos = childrenCategories.stream()
                .map(category -> new CategoryResponseDto(category))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public void createCategory(String name) {
        Optional<Category> found = categoryRepository.findByName(name);
        if (found.isPresent()) throw new IllegalArgumentException("중복된 카테고리명이 존재합니다.");
        Category category = new Category(name);
        categoryRepository.save(category);
    }

    public void createChildrenCategory(Long categoryId, String name) {
        Category parentCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        Optional<Category> found = categoryRepository.findByName(name);
        if (found.isPresent()) throw new IllegalArgumentException("중복된 카테고리명이 존재합니다.");

        int layer = parentCategory.getLayer() + 1;
        String parent = parentCategory.getName();
        Category category = new Category(name, layer, parent);
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Long categoryId, String name) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        Optional<Category> found = categoryRepository.findByName(name);
        if (found.isPresent()) throw new IllegalArgumentException("중복된 카테고리명이 존재합니다.");

        String parent = category.getName();
        List<Category> childrenCategoryList = categoryRepository.getCategoriesByParent(parent);
        for (Category childrenCategory : childrenCategoryList) {
            childrenCategory.updateChildrenCategoryParent(name);
        }
        category.updateCategoryName(name);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        String parent = category.getName();
        categoryRepository.deleteById(categoryId);
        categoryRepository.deleteByParent(parent);
    }
}
