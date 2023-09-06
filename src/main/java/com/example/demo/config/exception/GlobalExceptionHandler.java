package com.example.demo.config.exception;

import com.example.demo.config.exception.ex.DuplicatedException;
import com.example.demo.config.exception.ex.NotFoundTodoIdException;
import com.example.demo.config.exception.ex.NotFoundUserNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundUserNameException.class, NotFoundTodoIdException.class, DuplicatedException.class})
    public ResponseEntity<Map<String, String>> NotFoundException(RuntimeException runtimeException) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "404");
        map.put("message", runtimeException.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }




}
