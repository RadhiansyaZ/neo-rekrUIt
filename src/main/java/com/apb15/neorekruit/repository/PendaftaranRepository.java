package com.apb15.neorekruit.repository;

import com.apb15.neorekruit.model.Pendaftaran;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendaftaranRepository extends JpaRepository<Pendaftaran, Long> {
    @Override
    Optional<Pendaftaran> findById(Long id);
}
