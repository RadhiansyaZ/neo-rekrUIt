package com.apb15.neorekruit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Pengumuman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime waktu = LocalDateTime.now();

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String isi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rekrutmen_id", nullable = false)
    @JsonBackReference
    private Rekrutmen rekrutmen;

}
