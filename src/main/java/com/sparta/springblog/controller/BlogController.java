package com.sparta.springblog.controller;

import com.sparta.springblog.dto.PostingRequestDto;
import com.sparta.springblog.dto.CreateResponseDto;
import com.sparta.springblog.entity.Posting;
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

    @PostMapping("/api/posting")
    public CreateResponseDto createPosting(@RequestBody PostingRequestDto requestDto) {
        return blogService.createPosting(requestDto.getWriterName(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents());
    }

    @GetMapping("/api/postings")
    public List<Posting> getAllPostings() {
        return blogService.getAllPostings();
    }

    @GetMapping("/api/posting/id/{id}")
    public List<Posting> getPostingById(@PathVariable Long id) {
        return blogService.getPostingById(id);
    }

    @GetMapping("/api/posting/writerName/{writerName}")
    public List<Posting> getPostingByWriterName(@PathVariable String writerName) {
        return blogService.getPostingByWriterName(writerName);
    }

    @PutMapping("/api/posting/{id}/{password}")
    public String updatePosting(@PathVariable Long id, @PathVariable String password, @RequestBody PostingRequestDto requestDto) {
        return blogService.update(id, password, requestDto);
    }

    @DeleteMapping("/api/posting/{id}/{password}")
    public String deletePosting(@PathVariable Long id, @PathVariable String password) {
        return blogService.deletePosting(id, password);
    }

}
