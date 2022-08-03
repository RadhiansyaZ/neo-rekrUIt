package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.StatusPenerimaan;

public interface PendaftaranService {
    Pendaftaran createPendaftaran(Long idRekrutmen, String emailPendaftar, Pendaftaran pendaftaran);
    Pendaftaran findPendaftaranById(Long idPendaftaran);
    Pendaftaran updatePendaftaran(Long idRekrutmen, Long idPendaftaran, Pendaftaran pendaftaran);
    void changeStatusPendaftaran(Long idPendaftaran, StatusPenerimaan status);
}
