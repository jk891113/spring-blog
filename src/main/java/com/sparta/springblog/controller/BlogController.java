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

    @PostMapping("/posting")
    public CreateResponseDto createPosting(@RequestBody PostingRequestDto requestDto, HttpServletRequest request) {
        return blogService.createPosting(requestDto, request);
    }

    @GetMapping("/postings")
    public List<PostingResponseDto> getAllPostings() {
        return blogService.getAllPostings();
    }

    @GetMapping("/posting/id")
    public PostingResponseDto getPostingById(@RequestParam Long id) {
        return blogService.getPostingById(id);
    }

    @GetMapping("/posting/name")
    public List<PostingResponseDto> getPostingByWriterName(@RequestParam String writerName) {
        return blogService.getPostingByWriterName(writerName);
    }

    @PutMapping("/posting/{id}")
    public String updatePosting(@PathVariable Long id, @RequestParam String password, @RequestBody UpdateRequestDto requestDto) {
        return blogService.update(id, password, requestDto);
    }

    @DeleteMapping("/posting/{id}")
    public String deletePosting(@PathVariable Long id, @RequestParam String password) {
        return blogService.deletePosting(id, password);
    }

}
