package com.apb15.neorekruit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rekruter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pengguna_email", referencedColumnName = "email")
    private Pengguna pengguna;

    @Column(nullable = false)
    private String namaRekruter;

    @Column(nullable = false)
    private String deskripsiRekruter;
}
