package com.sparta.springblog.controller;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.requestdto.LoginRequestDto;
import com.sparta.springblog.requestdto.SignupRequestDto;
import com.sparta.springblog.responsedto.StatusResponseDto;
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
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView();
    }

    @PostMapping("/signup")
    public ResponseEntity<StatusResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) throws SQLIntegrityConstraintViolationException {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.CREATED, "회원가입 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        responseDto.setStatus(StatusEnum.CREATED);
//        responseDto.setMessage("회원가입 완료");

        userService.signup(requestDto);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView();
    }

    @PostMapping("/login")
    public ResponseEntity<StatusResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "로그인 성공");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        responseDto.setStatus(StatusEnum.OK);
//        responseDto.setMessage("로그인 성공");

        userService.login(requestDto, response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
