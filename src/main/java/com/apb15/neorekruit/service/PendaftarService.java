package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftar;

public interface PendaftarService {
    Pendaftar createPendaftar(Pendaftar pendaftar);
    Pendaftar findByEmail(String email);
}
