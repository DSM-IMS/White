package com.dsm.ims.controller;

import com.dsm.ims.exception.ApiErrorResponse;
import com.dsm.ims.exception.IdMismatchException;
import com.dsm.ims.exception.PasswordMismatchException;
import com.dsm.ims.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> unauthorizedExceptionHandle(UnauthorizedException ex) {
        ApiErrorResponse response = new ApiErrorResponse("Unauthorized Exception", "토큰이 유효하지 않거나 잘못됨 : " + ex.getStackTrace());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdMismatchException.class)
    public ResponseEntity<ApiErrorResponse> idMismatchExceptionHandler(IdMismatchException ex) {
        ApiErrorResponse response = new ApiErrorResponse("ID Mismatch Exception", "아이디가 일치하는 계정을 찾을 수 없음 : " + ex.getStackTrace());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiErrorResponse> idMismatchExceptionHandler(PasswordMismatchException ex) {
        ApiErrorResponse response = new ApiErrorResponse("Password Mismatch Exception", "비밀번호가 일치하는 계정을 찾을 수 없음 : " + ex.getStackTrace());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
