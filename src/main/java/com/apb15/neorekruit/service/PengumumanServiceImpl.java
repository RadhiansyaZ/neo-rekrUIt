package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pengumuman;
import com.apb15.neorekruit.repository.PengumumanRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PengumumanServiceImpl implements PengumumanService {
    private final RekrutmenService rekrutmenService;
    private final PengumumanRepository pengumumanRepository;

    @Override
    public Collection<Pengumuman> findAllPengumumanInRekrutmen(Long rekrutmenId) {
        var pengumuman = rekrutmenService.findAllPengumuman(rekrutmenId);
        return pengumuman;
    }

    @Override
    public Pengumuman findPengumumanById(Long rekrutmenId, Long pengumumanId) {
        var pengumumanOptional = pengumumanRepository.findById(pengumumanId);
        if(!pengumumanOptional.isPresent()) throw new ObjectNotFoundException(pengumumanId, "Pengumuman");

        var pengumuman = pengumumanOptional.get();
        return pengumuman;
    }

    @Override
    public Pengumuman createPengumuman(Long rekrutmenId, Pengumuman pengumuman) {
        var rekrutmen = rekrutmenService.findById(rekrutmenId);
        pengumuman.setRekrutmen(rekrutmen);

        var createdPengumuman = pengumumanRepository.save(pengumuman);

        return createdPengumuman;
    }

    @Override
    public Pengumuman updatePengumuman(Long rekrutmenId, Long pengumumanId, Pengumuman pengumuman) {
        var pengumumanOptional = pengumumanRepository.findById(pengumumanId);
        if(!pengumumanOptional.isPresent()) throw new ObjectNotFoundException(pengumumanId, "Pengumuman");

        var oldPengumuman = pengumumanOptional.get();
        oldPengumuman.setJudul(pengumuman.getJudul());
        oldPengumuman.setIsi(pengumuman.getIsi());
        oldPengumuman.setWaktu(LocalDateTime.now());
        var updatedPengumuman = pengumumanRepository.save(oldPengumuman);

        return updatedPengumuman;
    }

    @Override
    public void deletePengumuman(Long rekrutmenId, Long pengumumanId) {
        var pengumumanOptional = pengumumanRepository.findById(pengumumanId);
        if(!pengumumanOptional.isPresent()) throw new ObjectNotFoundException(pengumumanId, "Pengumuman");

        pengumumanRepository.deleteById(pengumumanId);
    }
}
