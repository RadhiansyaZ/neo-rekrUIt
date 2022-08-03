package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.StatusPenerimaan;
import com.apb15.neorekruit.repository.PendaftaranRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PendaftaranServiceImpl implements PendaftaranService {
    private final RekrutmenService rekrutmenService;
    private final PendaftarService pendaftarService;
    private final PendaftaranRepository pendaftaranRepository;

    @Override
    public Pendaftaran createPendaftaran(Long idRekrutmen, String emailPendaftar, Pendaftaran pendaftaran) {
        var rekrutmen = rekrutmenService.findById(idRekrutmen);
        pendaftaran.setRekrutmen(rekrutmen);
        var pendaftar = pendaftarService.findByEmail(emailPendaftar);
        pendaftaran.setPendaftar(pendaftar);
        var createdPendaftaran = pendaftaranRepository.save(pendaftaran);

        return createdPendaftaran;
    }

    @Override
    public Pendaftaran findPendaftaranById(Long idPendaftaran) {
        var pendaftaranOptional = pendaftaranRepository.findById(idPendaftaran);
        if(!pendaftaranOptional.isPresent()) throw new ObjectNotFoundException(idPendaftaran, "Pendaftaran");

        var pendaftaran = pendaftaranOptional.get();
        return pendaftaran;
    }

    @Override
    public Pendaftaran updatePendaftaran(Long idRekrutmen, Long idPendaftaran, Pendaftaran pendaftaran) {
        var oldPendaftaran = findPendaftaranById(idPendaftaran);
        oldPendaftaran.setLinkCV(pendaftaran.getLinkCV());
        oldPendaftaran.setLinkTugas(pendaftaran.getLinkTugas());

        var updatedPendaftaran = pendaftaranRepository.save(oldPendaftaran);
        return updatedPendaftaran;
    }

    @Override
    public void changeStatusPendaftaran(Long idPendaftaran, StatusPenerimaan status) {
        var pendaftaran = findPendaftaranById(idPendaftaran);
        pendaftaran.setStatusPenerimaan(status);
        pendaftaranRepository.save(pendaftaran);
    }
}
