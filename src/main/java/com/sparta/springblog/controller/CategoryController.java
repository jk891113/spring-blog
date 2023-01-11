package com.sparta.springblog.controller;

import com.sparta.springblog.dto.request.CategoryRequestDto;
import com.sparta.springblog.dto.response.CategoryListResponseDto;
import com.sparta.springblog.dto.response.CategoryResponseDto;
import com.sparta.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryListResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/main")
    public List<CategoryResponseDto> getMainCategories() {
        return categoryService.getMainCategories();
    }

    @GetMapping("/categories/{categoryId}")
    public List<CategoryResponseDto> getChildrenCategories(@PathVariable Long categoryId) {
        return categoryService.getChildrenCategories(categoryId);
    }

    @PostMapping("/categories")
    public void createCategory(@RequestBody CategoryRequestDto requestDto,
                               @AuthenticationPrincipal UserDetails userDetails) {
        categoryService.createCategory(requestDto.getName());
    }

    @PostMapping("/categories/{categoryId}")
    public void createChildrenCategory(@PathVariable Long categoryId,
                                       @RequestBody CategoryRequestDto requestDto,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        categoryService.createChildrenCategory(categoryId, requestDto.getName());
    }

    @PutMapping("/categories/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId,
                               @RequestBody CategoryRequestDto requestDto,
                               @AuthenticationPrincipal UserDetails userDetails) {
        categoryService.updateCategory(categoryId, requestDto.getName());
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId,
                               @AuthenticationPrincipal UserDetails userDetails) {
        categoryService.deleteCategory(categoryId);
    }
}
