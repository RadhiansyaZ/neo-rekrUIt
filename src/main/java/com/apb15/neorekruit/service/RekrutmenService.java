package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.Rekrutmen;

import java.util.Collection;

public interface RekrutmenService {
    Collection<Rekrutmen> findAllRekrutmen();
    Rekrutmen createRekrutmen(String emailRekruter, Rekrutmen rekrutmen);
    Collection<Pendaftaran> findAllPendaftaran(Long idRekrutmen);
    Rekrutmen updateRekrutmen(String emailRekruter, Long idRekrutmen, Rekrutmen rekrutmen);
    void deleteRekrutmen(Long idRekrutmen);
}
