package com.sparta.springblog.controller;

import com.sparta.springblog.dto.CreateResponseDto;
import com.sparta.springblog.dto.PostingRequestDto;
import com.sparta.springblog.dto.PostingResponseDto;
import com.sparta.springblog.dto.UpdateRequestDto;
import com.sparta.springblog.service.BlogService;
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
    public CreateResponseDto createPosting(@RequestBody PostingRequestDto requestDto) {
        return blogService.createPosting(requestDto.getWriterName(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents());
    }

    @GetMapping("/postings")
    public List<PostingResponseDto> getAllPostings() {
        return blogService.getAllPostings();
    }

    @GetMapping("/posting/id/{id}")
    public PostingResponseDto getPostingById(@PathVariable Long id) {
        return blogService.getPostingById(id);
    }

    @GetMapping("/posting/writerName/{writerName}")
    public List<PostingResponseDto> getPostingByWriterName(@PathVariable String writerName) {
        return blogService.getPostingByWriterName(writerName);
    }

    @PutMapping("/posting/{id}/{password}")
    public String updatePosting(@PathVariable Long id, @PathVariable String password, @RequestBody UpdateRequestDto requestDto) {
        return blogService.update(id, password, requestDto);
    }

    @DeleteMapping("/posting/{id}/{password}")
    public String deletePosting(@PathVariable Long id, @PathVariable String password) {
        return blogService.deletePosting(id, password);
    }

}
