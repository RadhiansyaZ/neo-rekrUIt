package com.apb15.neorekruit.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pendaftar {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pengguna_email", referencedColumnName = "email")
    private Pengguna pengguna;

    @Column(nullable = false)
    private String fakultas; // good if use ENUM

    @Column(nullable = false)
    private String namaLengkap;

    @Column(nullable = false, unique = true)
    private String npm;

    @Column
    private String kontak;

    @OneToMany(mappedBy = "pendaftar")
    @JsonManagedReference("pendaftaran-pendaftar")
    private Collection<Pendaftaran> mendaftar = new ArrayList<>();
}
