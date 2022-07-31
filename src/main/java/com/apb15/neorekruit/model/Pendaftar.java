package com.apb15.neorekruit.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Pendaftar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Pengguna user;

    @Column(nullable = false)
    private String fakultas; // good if use ENUM

    @Column(nullable = false)
    private String namaLengkap;

    @Column(nullable = false, unique = true)
    private String npm;

    @Column
    private String kontak;

    @OneToMany(mappedBy = "pendaftar", orphanRemoval = true)
    private Collection<Pendaftaran> mendaftar = new ArrayList<>();

}
