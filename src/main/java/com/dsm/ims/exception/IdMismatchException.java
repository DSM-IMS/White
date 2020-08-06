package com.dsm.ims.exception;

public class IdMismatchException extends RuntimeException {
    public IdMismatchException() {
        super("아이디가 일치하지 않습니다.");
    }
}