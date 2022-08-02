package com.apb15.neorekruit.repository;

import com.apb15.neorekruit.model.Rekruter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RekruterRepository extends JpaRepository<Rekruter, Long> {
    Optional<Rekruter> findRekruterByPenggunaEmail(String email);
}
