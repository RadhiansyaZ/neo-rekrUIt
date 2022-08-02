package com.apb15.neorekruit.dto.pengguna;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegisterRekruterRequest {
    private String email;

    private String password;

    @JsonProperty("nama_rekruter")
    private String namaRekruter;

    @JsonProperty("deskripsi_rekruter")
    private String deskripsiRekruter;
}
