package com.apb15.neorekruit.dto.pengguna;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegisterPendaftarRequest {
    private String email;
    private String password;
    @JsonProperty("nama_lengkap")
    private String namaLengkap;
    private String npm;
    private String fakultas;
    private String kontak;
}
