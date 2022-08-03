package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.Pengumuman;
import com.apb15.neorekruit.model.Rekrutmen;
import com.apb15.neorekruit.repository.RekrutmenRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RekrutmenServiceImpl implements RekrutmenService {
    private final RekrutmenRepository rekrutmenRepository;
    private final RekruterService rekruterService;

    @Override
    public Collection<Rekrutmen> findAllRekrutmen() {
        return rekrutmenRepository.findAll();
    }

    @Override
    public Rekrutmen findById(Long idRekrutmen) {
        var rekrutmenOptional = rekrutmenRepository.findById(idRekrutmen);
        if(!rekrutmenOptional.isPresent()) throw new ObjectNotFoundException(idRekrutmen, "Rekrutmen");

        return rekrutmenOptional.get();
    }

    @Override
    public Rekrutmen createRekrutmen(String emailRekruter, Rekrutmen rekrutmen) {
        var rekruter = rekruterService.findByEmail(emailRekruter);
        rekrutmen.setRekruter(rekruter);
        var createdRekrutmen = rekrutmenRepository.save(rekrutmen);
        return createdRekrutmen;
    }

    @Override
    public Collection<Pendaftaran> findAllPendaftaran(Long idRekrutmen) {
        var rekrutmen = rekrutmenRepository.findById(idRekrutmen);
        if(!rekrutmen.isPresent()) {
            throw new IllegalStateException("Rekrutmen not found");
        }
        var pendaftaranInRekrutmen = rekrutmen.get().getPendaftaranRekrutmen();
        return pendaftaranInRekrutmen;
    }

    @Override
    public Rekrutmen updateRekrutmen(Long idRekrutmen, Rekrutmen rekrutmen) {
        var rekrutmenOptional = rekrutmenRepository.findById(idRekrutmen);
        if(!rekrutmenOptional.isPresent()) {
            throw new IllegalStateException("Rekrutmen not found");
        }

        var rekrutmenQueried = rekrutmenOptional.get();
        rekrutmenQueried.setStatus(rekrutmen.getStatus());
        rekrutmenQueried.setJudul(rekrutmen.getJudul());
        rekrutmenQueried.setNarahubung(rekrutmen.getNarahubung());
        rekrutmenQueried.setDeskripsiTugas(rekrutmen.getDeskripsiTugas());
        rekrutmenQueried.setDeskripsiPekerjaan(rekrutmen.getDeskripsiPekerjaan());
        rekrutmenQueried.setSyaratDanKetentuan(rekrutmen.getSyaratDanKetentuan());
        rekrutmenQueried.setStartDateRegistrasi(rekrutmen.getStartDateRegistrasi());
        rekrutmenQueried.setDueDateRegistrasi(rekrutmen.getDueDateRegistrasi());
        rekrutmenQueried.setDueDateTugas(rekrutmen.getDueDateTugas());
        rekrutmenQueried.setLinkWawancara(rekrutmen.getLinkWawancara());

        var updatedRekrutmen = rekrutmenRepository.save(rekrutmenQueried);
        return updatedRekrutmen;
    }

    @Override
    public void deleteRekrutmen(Long idRekrutmen) {
        var rekrutmen = rekrutmenRepository.findById(idRekrutmen);
        if(!rekrutmen.isPresent()) {
            throw new IllegalStateException("Rekrutmen not found");
        }
        rekrutmenRepository.deleteById(idRekrutmen);
    }

    @Override
    public Collection<Pengumuman> findAllPengumuman(Long idRekrutmen) {
        var rekrutmenOptional = rekrutmenRepository.findById(idRekrutmen);

        if(!rekrutmenOptional.isPresent()) throw new ObjectNotFoundException(idRekrutmen, "Rekrutmen");

        var pengumuman = rekrutmenOptional.get().getPengumumanRekrutmen();

        return pengumuman;
    }
}
