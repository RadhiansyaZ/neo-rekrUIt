package com.apb15.neorekruit.controller;

import com.apb15.neorekruit.dto.pendaftaran.UpdateStatus;
import com.apb15.neorekruit.model.Pendaftaran;
import com.apb15.neorekruit.security.JWTUtils;
import com.apb15.neorekruit.service.PendaftaranService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rekrutmen/{idRekrutmen}/pendaftaran")
@RequiredArgsConstructor
public class PendaftaranController {
    private final PendaftaranService pendaftaranService;

    @PostMapping
    public ResponseEntity<Pendaftaran> createPendaftaran(@PathVariable("idRekrutmen") Long idRekrutmen,
                                                         @RequestBody Pendaftaran pendaftaran,
                                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        try {
            var token = authHeader.substring("Bearer ".length());
            var emailPendaftar = JWTUtils.decodeJWTToken(token).getSubject();

            var createdPendaftaran = pendaftaranService.createPendaftaran(idRekrutmen, emailPendaftar, pendaftaran);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(String.format("/rekrutmen/%s/pendaftaran", idRekrutmen))
                    .toUriString());

            return ResponseEntity.created(uri).body(createdPendaftaran);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot create pendaftaran", exception);
        }
    }

    @GetMapping("/{idPendaftaran}")
    public ResponseEntity<Pendaftaran> getPendaftaranForRekrutmenById(@PathVariable("idPendaftaran") Long idPendaftaran) {
        try {
            var pendaftaran = pendaftaranService.findPendaftaranById(idPendaftaran);

            return ResponseEntity.ok().body(pendaftaran);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idPendaftaran}")
    public ResponseEntity<Pendaftaran> updatePendaftaran(@PathVariable("idRekrutmen") Long idRekrutmen,
                                                         @PathVariable("idPendaftaran") Long idPendaftaran,
                                                         @RequestBody Pendaftaran pendaftaran) {
        try {
            var updatedPengumuman = pendaftaranService.updatePendaftaran(idRekrutmen, idPendaftaran, pendaftaran);

            return ResponseEntity.ok().body(updatedPengumuman);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot update pendaftaran", exception);
        }
    }

    @PostMapping("/{idPendaftaran}/ubahstatus")
    public ResponseEntity<?> ubahStatusPendaftaran(@PathVariable("idPendaftaran") Long idPendaftaran,
                                                   @RequestBody UpdateStatus status) {
        try {
            pendaftaranService.changeStatusPendaftaran(idPendaftaran, status.getStatus());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pendaftaran not found", exception);
        }
    }
}
