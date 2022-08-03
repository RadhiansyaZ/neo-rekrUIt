package com.apb15.neorekruit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Pendaftaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonProperty("link_cv")
    private String linkCV;

    @Column
    @JsonProperty("link_tugas")
    private String linkTugas;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPenerimaan statusPenerimaan = StatusPenerimaan.DIPROSES;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pendaftar_id", nullable = false)
    @JsonBackReference("pendaftaran-pendaftar")
    private Pendaftar pendaftar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rekrutmen_id", nullable = false)
    @JsonBackReference("pendaftaran-rekrutmen")
    private Rekrutmen rekrutmen;

}
