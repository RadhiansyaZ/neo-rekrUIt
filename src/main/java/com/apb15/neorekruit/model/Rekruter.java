package com.apb15.neorekruit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Rekruter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Pengguna user;

    @Column(nullable = false)
    private String namaRekruter;

    @Column(nullable = false)
    private String deskripsiRekruter;
}
