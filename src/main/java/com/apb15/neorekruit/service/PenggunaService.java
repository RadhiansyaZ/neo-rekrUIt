package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pengguna;
import com.apb15.neorekruit.model.Role;

import java.util.List;

public interface PenggunaService {
    Pengguna save(Pengguna pengguna);

    Pengguna getPengguna(String email);
    List<Pengguna> getAllPengguna();
}
