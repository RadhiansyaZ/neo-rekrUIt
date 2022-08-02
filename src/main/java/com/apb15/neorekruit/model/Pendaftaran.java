package com.apb15.neorekruit.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Pendaftaran {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String linkCV;

    @Column
    private String linkTugas;

    @Column
    private String statusPenerimaan; // Good if ENUM

    @Column
    private String nilai;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pendaftar_id", nullable = false)
    private Pendaftar pendaftar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rekrutmen_id", nullable = false)
    private Rekrutmen rekrutmen;

}
