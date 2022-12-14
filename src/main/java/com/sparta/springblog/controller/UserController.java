package com.sparta.springblog.controller;

import com.sparta.springblog.requestdto.LoginRequestDto;
import com.sparta.springblog.requestdto.SignupRequestDto;
import com.sparta.springblog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public void signup(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView();
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
    }
}