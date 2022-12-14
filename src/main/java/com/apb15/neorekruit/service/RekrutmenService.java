package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.Pengumuman;
import com.apb15.neorekruit.model.Rekrutmen;

import java.util.Collection;

public interface RekrutmenService {
    Collection<Rekrutmen> findAllRekrutmen();
    Rekrutmen findById(Long id);
    Rekrutmen createRekrutmen(String emailRekruter, Rekrutmen rekrutmen);
    Collection<Pendaftaran> findAllPendaftaran(Long idRekrutmen);
    Rekrutmen updateRekrutmen(Long idRekrutmen, Rekrutmen rekrutmen);
    void deleteRekrutmen(Long idRekrutmen);
    Collection<Pengumuman> findAllPengumuman(Long idRekrutmen);
}
