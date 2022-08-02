package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftar;
import com.apb15.neorekruit.repository.PendaftarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PendaftarServiceImpl implements PendaftarService {
    private final PendaftarRepository pendaftarRepository;

    @Override
    public Pendaftar createPendaftar(Pendaftar pendaftar) {
        return pendaftarRepository.save(pendaftar);
    }
}
