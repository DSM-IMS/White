package com.dsm.ims.util;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("토큰이 유효하지 않습니다.");
    }
}
