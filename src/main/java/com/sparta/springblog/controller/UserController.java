package com.sparta.springblog.controller;

import com.sparta.springblog.requestdto.LoginRequestDto;
import com.sparta.springblog.requestdto.SignupRequestDto;
import com.sparta.springblog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        userService.signup(requestDto);
        return ResponseEntity.ok().headers(headers).body("success");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
