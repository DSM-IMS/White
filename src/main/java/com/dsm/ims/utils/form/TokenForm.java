package com.dsm.ims.utils.form;

import io.swagger.annotations.ApiParam;

public class TokenForm {

    @ApiParam(value = "리프레시 토큰", required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}