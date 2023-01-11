package com.sparta.springblog.controller;

import com.sparta.springblog.dto.request.CategoryRequestDto;
import com.sparta.springblog.dto.response.CategoryListResponseDto;
import com.sparta.springblog.dto.response.CategoryResponseDto;
import com.sparta.springblog.dto.response.StatusResponseDto;
import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
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
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto,
                               @AuthenticationPrincipal UserDetails userDetails) {
        return categoryService.createCategory(requestDto.getName());
    }

    @PostMapping("/categories/{categoryId}")
    public CategoryResponseDto createChildrenCategory(@PathVariable Long categoryId,
                                       @RequestBody CategoryRequestDto requestDto,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return categoryService.createChildrenCategory(categoryId, requestDto.getName());
    }

    @PutMapping("/categories/{categoryId}")
    public CategoryResponseDto updateCategory(@PathVariable Long categoryId,
                               @RequestBody CategoryRequestDto requestDto,
                               @AuthenticationPrincipal UserDetails userDetails) {
        return categoryService.updateCategory(categoryId, requestDto.getName());
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<StatusResponseDto> deleteCategory(@PathVariable Long categoryId,
                               @AuthenticationPrincipal UserDetails userDetails) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "삭제 성공");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
