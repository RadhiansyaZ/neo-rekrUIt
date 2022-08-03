package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pengumuman;

import java.util.Collection;

public interface PengumumanService {
    Collection<Pengumuman> findAllPengumumanInRekrutmen(Long rekrutmenId);
    Pengumuman findPengumumanById(Long rekrutmenId, Long pengumumanId);
    Pengumuman createPengumuman(Long rekrutmenId, Pengumuman pengumuman);
    Pengumuman updatePengumuman(Long rekrutmenId, Long pengumumanId, Pengumuman pengumuman);
    void deletePengumuman(Long rekrutmenId, Long pengumumanId);
}
