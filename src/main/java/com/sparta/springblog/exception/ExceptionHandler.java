package com.sparta.springblog.exception;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.responsedto.StatusResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.Charset;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public StatusResponseDto IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        StatusResponseDto responseDto = new StatusResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseDto.setStatus(StatusEnum.BAD_REQUEST);
        responseDto.setMessage(e.getMessage());
        return responseDto;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public StatusResponseDto MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        StatusResponseDto responseDto = new StatusResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseDto.setStatus(StatusEnum.BAD_REQUEST);
        responseDto.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return responseDto;
    }
}
