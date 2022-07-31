package com.apb15.neorekruit.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Pengumuman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate waktu;

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String isi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rekrutmen_id", nullable = false)
    private Rekrutmen rekrutmen;

}
