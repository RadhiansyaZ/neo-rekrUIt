package com.apb15.neorekruit.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Rekrutmen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String status;

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String narahubung;

    @Column
    private String deskripsiTugas;

    @Column
    private String deskripsiPekerjaan;

    @Column
    private String SyaratDanKetentuan;

    @Column(nullable = false)
    private Timestamp startDateRegistrasi;

    @Column(nullable = false)
    private Timestamp dueDateRegistrasi;

    @Column(nullable = false)
    private Timestamp dueDateTugas;

    @Column(nullable = false)
    private String linkWawancara;

    @OneToMany(mappedBy = "rekrutmen", orphanRemoval = true)
    private Collection<Pendaftaran> pendaftaranRekrutmen = new ArrayList<>();

    @OneToMany(mappedBy = "rekrutmen", orphanRemoval = true)
    private Collection<Pengumuman> pengumumanRekrutmen = new ArrayList<>();

}
