package com.sparta.springblog.exception;

import com.sparta.springblog.enums.StatusEnum;
import com.sparta.springblog.responsedto.StatusResponseDto;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.Charset;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StatusResponseDto> IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.BAD_REQUEST, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponseDto> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.BAD_REQUEST, message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IdentifierGenerationException.class)
    public ResponseEntity<StatusResponseDto> IdentifierGenerationExceptionHandler(IdentifierGenerationException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.BAD_REQUEST, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<StatusResponseDto> SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        String message = e.getMessage();
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.BAD_REQUEST, message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StatusResponseDto> AccessDeniedExceptionHandler(AccessDeniedException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.FORBIDDEN, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(SecurityException.class)
    public ResponseEntity<StatusResponseDto> SecurityExceptionHandler(SecurityException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.UNAUTHORIZE, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<StatusResponseDto> MalformedJwtExceptionHandler(MalformedJwtException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.UNAUTHORIZE, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.UNAUTHORIZED);
    }

//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<StatusResponseDto> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
//        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.UNAUTHORIZE, "Token is empty.");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        return new ResponseEntity<>(responseDto, headers, HttpStatus.UNAUTHORIZED);
//    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StatusResponseDto> NullPointerExceptionHandler(NullPointerException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.UNAUTHORIZE, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.UNAUTHORIZED);
    }
}
