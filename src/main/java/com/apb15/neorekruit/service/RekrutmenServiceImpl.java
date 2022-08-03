package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.Pengumuman;
import com.apb15.neorekruit.model.Rekruter;
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
    public Rekrutmen updateRekrutmen(String emailRekruter, Long idRekrutmen, Rekrutmen rekrutmen) {
        var isRekrutmenExist = rekrutmenRepository.findById(idRekrutmen);
        if(!isRekrutmenExist.isPresent()) {
            throw new IllegalStateException("Rekrutmen not found");
        }

        var rekruter = rekruterService.findByEmail(emailRekruter);
        rekrutmen.setRekruter(rekruter);
        rekrutmen.setId(isRekrutmenExist.get().getId());
        var updatedRekrutmen = rekrutmenRepository.save(rekrutmen);
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
