package com.dsm.ims.form;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class AccessTokenReissuanceResultForm {

    @ApiModelProperty(example = "H1H1H1H1H1H.H1H1H1H1H.HH1HH1H1HH1H", required = true)
    private String accessToken;
    @ApiModelProperty(example = "2003-08-16T21:30:32", required = true)
    private LocalDateTime accessTokenExpiration;

    public AccessTokenReissuanceResultForm() {}
    public AccessTokenReissuanceResultForm(String accessToken, LocalDateTime accessTokenExpiration) {
        this.accessToken = accessToken;
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDateTime getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public void setAccessTokenExpiration(LocalDateTime accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }
}
