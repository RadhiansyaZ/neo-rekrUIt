package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftar;
import com.apb15.neorekruit.repository.PendaftarRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PendaftarServiceImpl implements PendaftarService {
    private final PenggunaService penggunaService;
    private final PendaftarRepository pendaftarRepository;

    @Override
    public Pendaftar createPendaftar(Pendaftar pendaftar) {
        return pendaftarRepository.save(pendaftar);
    }

    @Override
    public Pendaftar findByEmail(String email) {
        var pengguna = penggunaService.getPengguna(email);
        var pendaftar = pendaftarRepository.findPendaftarByPengguna(pengguna);

        if(!pendaftar.isPresent()) throw new ObjectNotFoundException(email, "Pendaftar");

        return pendaftar.get();
    }
}
