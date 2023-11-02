package com.example.demo.config.exception;

import com.example.demo.common.CommonResponse;
import com.example.demo.config.exception.ex.DuplicatedException;
import com.example.demo.config.exception.ex.LoginErrorException;
import com.example.demo.config.exception.ex.NotFoundTodoIdException;
import com.example.demo.config.exception.ex.NotFoundUserNameException;
import com.example.demo.dto.ResponseDto;
import io.swagger.models.Response;
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
    @ExceptionHandler(value = NotFoundUserNameException.class)
    public ResponseEntity<ResponseDto> NotFoundUserException(NotFoundUserNameException notFoundUserNameException) {
        log.error(notFoundUserNameException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(CommonResponse.FAIL, notFoundUserNameException.getMessage()));
    }

    @ExceptionHandler(value = NotFoundTodoIdException.class)
    public ResponseEntity<ResponseDto> NotFoundTodoException(NotFoundTodoIdException notFoundTodoIdException){
        log.error(notFoundTodoIdException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(CommonResponse.FAIL, notFoundTodoIdException.getMessage() ));
    }

    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity<ResponseDto> DuplicationException(DuplicatedException duplicatedException){
        log.error(duplicatedException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(CommonResponse.FAIL, duplicatedException.getMessage()));
    }

    @ExceptionHandler(value = LoginErrorException.class)
    public ResponseEntity<ResponseDto> LoginErrorException(LoginErrorException loginErrorException){
        log.error(loginErrorException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(CommonResponse.FAIL, loginErrorException.getMessage()));
    }


}
