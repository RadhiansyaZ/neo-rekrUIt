package com.apb15.neorekruit.controller;

import com.apb15.neorekruit.dto.pengguna.RegisterPendaftarRequest;
import com.apb15.neorekruit.dto.pengguna.RegisterRekruterRequest;
import com.apb15.neorekruit.model.Pendaftar;
import com.apb15.neorekruit.model.Pengguna;
import com.apb15.neorekruit.model.Rekruter;
import com.apb15.neorekruit.model.Role;
import com.apb15.neorekruit.service.PendaftarService;
import com.apb15.neorekruit.service.PenggunaService;
import com.apb15.neorekruit.service.RekruterService;
import com.apb15.neorekruit.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/pengguna")
@RequiredArgsConstructor
public class PenggunaController {
    private final PenggunaService penggunaService;
    private final RoleService roleService;
    private final RekruterService rekruterService;
    private final PendaftarService pendaftarSevice;

    @GetMapping
    public ResponseEntity<List<Pengguna>> getAllPengguna() {
        return ResponseEntity.ok().body(penggunaService.getAllPengguna());
    }

    @PostMapping("/rekruter")
    @ResponseBody
    public ResponseEntity<?> createRekruter(@RequestBody RegisterRekruterRequest registerRekruterRequest) {
        var role = roleService.findOrCreate("ROLE_REKRUTER");
        Collection<Role> roleCollection = new ArrayList<>();
        roleCollection.add(role);

        var pengguna = Pengguna.builder()
                .email(registerRekruterRequest.getEmail())
                .password(registerRekruterRequest.getPassword())
                .roles(roleCollection)
                        .build();

        try {
            var createdPengguna = penggunaService.save(pengguna);

            var rekruter = Rekruter.builder()
                    .pengguna(createdPengguna)
                    .namaRekruter(registerRekruterRequest.getNamaRekruter())
                    .deskripsiRekruter(registerRekruterRequest.getDeskripsiRekruter())
                    .build();

            var createdRekruter = rekruterService.createRekruter(rekruter);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/pengguna/rekruter")
                    .toUriString());

            return ResponseEntity.created(uri).body(createdRekruter);
        } catch (IllegalStateException exception) {
            return null;
        }
    }

    @PostMapping("/pendaftar")
    @ResponseBody
    public ResponseEntity<?> createPendaftar(@RequestBody RegisterPendaftarRequest registerPendaftarRequest) {
        var role = roleService.findOrCreate("ROLE_REKRUTER");
        Collection<Role> roleCollection = new ArrayList<>();
        roleCollection.add(role);

        var pengguna = Pengguna.builder()
                .email(registerPendaftarRequest.getEmail())
                .password(registerPendaftarRequest.getPassword())
                .roles(roleCollection)
                        .build();

        try {
            var createdPengguna = penggunaService.save(pengguna);

            var pendaftar = Pendaftar.builder()
                    .pengguna(createdPengguna)
                    .namaLengkap(registerPendaftarRequest.getNamaLengkap())
                    .npm(registerPendaftarRequest.getNpm())
                    .fakultas(registerPendaftarRequest.getFakultas())
                    .kontak(registerPendaftarRequest.getKontak())
                    .build();

            var createdPendaftar = pendaftarSevice.createPendaftar(pendaftar);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/pengguna/pendaftar")
                    .toUriString());

            return ResponseEntity.created(uri).body(createdPendaftar);
        } catch (IllegalStateException exception) {
            return null;
        }
    }

    @PostMapping("/pengguna")
    public ResponseEntity<Pengguna> createPengguna(@RequestBody Pengguna pengguna) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/pengguna").toUriString());
        return ResponseEntity.created(uri).body(penggunaService.save(pengguna));
    }
}
