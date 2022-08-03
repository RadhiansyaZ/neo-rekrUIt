package com.apb15.neorekruit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "rekruter", referencedColumnName = "pengguna_email", nullable = false)
    @JsonBackReference
    private Rekruter rekruter;

    @Column
    private String status = "Belum Dibuka";

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String narahubung;

    @Column
    @JsonProperty("deskripsi_tugas")
    private String deskripsiTugas;

    @Column
    @JsonProperty("deskripsi_pekerjaan")
    private String deskripsiPekerjaan;

    @Column
    @JsonProperty("syarat_ketentuan")
    private String SyaratDanKetentuan;

    @Column(nullable = false)
    @JsonProperty("start_date_registrasi")
    private Timestamp startDateRegistrasi;

    @Column(nullable = false)
    @JsonProperty("due_date_registrasi")
    private Timestamp dueDateRegistrasi;

    @Column(nullable = false)
    @JsonProperty("due_date_tugas")
    private Timestamp dueDateTugas;

    @Column(nullable = false)
    @JsonProperty("link_wawancara")
    private String linkWawancara;

    @JsonIgnore
    @OneToMany(mappedBy = "rekrutmen", orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Pendaftaran> pendaftaranRekrutmen = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "rekrutmen", orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Pengumuman> pengumumanRekrutmen = new ArrayList<>();
}
