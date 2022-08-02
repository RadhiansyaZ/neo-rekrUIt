package com.apb15.neorekruit.repository;

import com.apb15.neorekruit.model.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findByEmail(String email);
}
