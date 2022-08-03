package com.apb15.neorekruit.repository;

import com.apb15.neorekruit.model.Pengumuman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PengumumanRepository extends JpaRepository<Pengumuman, Long> {
    @Override
    Optional<Pengumuman> findById(Long id);
    @Override
    void deleteById(Long id);
}
