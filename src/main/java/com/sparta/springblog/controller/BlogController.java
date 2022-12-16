package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.responsedto.CreateResponseDto;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
import com.sparta.springblog.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
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

    @GetMapping("/postings/id")
    public PostingResponseDto getPostingById(@RequestParam Long id) {
        return blogService.getPostingById(id);
    }

    @GetMapping("/postings/name")
    public List<PostingResponseDto> getPostingByUsername(@RequestParam String username) {
        return blogService.getPostingByUsername(username);
    }

    @PutMapping("/postings/{id}")
    public String updatePosting(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto, HttpServletRequest request) {
        return blogService.update(id, requestDto, request);
    }

    @DeleteMapping("/postings/{id}")
    public ResponseEntity<StatusResponseDto> deletePosting(@PathVariable Long id, HttpServletRequest request) {
        StatusResponseDto responseDto = new StatusResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseDto.setStatus(StatusEnum.OK);
        responseDto.setMessage("삭제 성공");

        blogService.deletePosting(id, request);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

}
