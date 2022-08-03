package com.apb15.neorekruit.repository;

import com.apb15.neorekruit.model.Pendaftar;
import com.apb15.neorekruit.model.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendaftarRepository extends JpaRepository<Pendaftar, Long> {
    Optional<Pendaftar> findPendaftarByPengguna(Pengguna pengguna);
}
