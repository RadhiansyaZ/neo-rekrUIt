package com.apb15.neorekruit.repository;

import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.model.Rekrutmen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RekrutmenRepository extends JpaRepository<Rekrutmen, Long> {
    @Override
    List<Rekrutmen> findAll();
    @Override
    Optional<Rekrutmen> findById(Long id);
    @Override
    void deleteById(Long id);
}
