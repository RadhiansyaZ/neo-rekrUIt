package com.apb15.neorekruit.dto.pengguna;

import lombok.Builder;

@Builder
public class JWTResponse {
    private final String accessToken;
    private final String refreshToken;

    @Override
    public String toString() {
        return String.format(
                "{\"access_token\": \"%s\", \"refresh_token\": \"%s\"}",
                this.accessToken,
                this.refreshToken
                );
    }
}
