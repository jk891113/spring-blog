package com.sparta.springblog.controller;

import com.sparta.springblog.responsedto.CreateResponseDto;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    @PostMapping("/postings")
    public CreateResponseDto createPosting(@RequestBody PostingRequestDto requestDto, HttpServletRequest request) {
        return blogService.createPosting(requestDto, request);
    }

    @GetMapping("/postings")
    public List<PostingResponseDto> getAllPostings() {
        return blogService.getAllPostings();
    }

    @GetMapping("/postings")
    public PostingResponseDto getPostingById(@RequestParam Long id) {
        return blogService.getPostingById(id);
    }

    @GetMapping("/postings")
    public List<PostingResponseDto> getPostingByUsername(@RequestParam String username) {
        return blogService.getPostingByUsername(username);
    }

    @PutMapping("/postings/{id}")
    public String updatePosting(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto, HttpServletRequest request) {
        return blogService.update(id, requestDto, request);
    }

    @DeleteMapping("/postings/{id}")
    public String deletePosting(@PathVariable Long id, HttpServletRequest request) {
        return blogService.deletePosting(id, request);
    }

}
