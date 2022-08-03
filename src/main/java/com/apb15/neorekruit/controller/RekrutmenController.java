package com.apb15.neorekruit.controller;

import com.apb15.neorekruit.model.Rekrutmen;
import com.apb15.neorekruit.security.JWTUtils;
import com.apb15.neorekruit.service.RekrutmenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rekrutmen")
@RequiredArgsConstructor
public class RekrutmenController {
    private final RekrutmenService rekrutmenService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> listAllRekrutmen(){
        var allRekrutmen = rekrutmenService.findAllRekrutmen();
        return ResponseEntity.ok().body(allRekrutmen);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Rekrutmen> createRekrutmen(@RequestBody Rekrutmen rekrutmen,
                                                     @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        var decodedJWT = JWTUtils.decodeJWTToken(authHeader.substring("Bearer ".length()));
        var emailFromJWT = decodedJWT.getSubject();

        var createdRekrutmen = rekrutmenService.createRekrutmen(emailFromJWT, rekrutmen);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/rekrutmen")
                    .toUriString());
        return ResponseEntity.created(uri).body(createdRekrutmen);
    }

    @GetMapping("/{idRekrutmen}")
    @ResponseBody
    public ResponseEntity<?> listAllPendaftarRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen) {
        try {
            var allPendaftaran = rekrutmenService.findAllPendaftaran(idRekrutmen);
            return ResponseEntity.ok().body(allPendaftaran);
        } catch (IllegalStateException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idRekrutmen}")
    @ResponseBody
    public ResponseEntity<Rekrutmen> updateRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen,
                                                     @RequestBody Rekrutmen rekrutmen) {
        try {
            var updatedRekrutmen = rekrutmenService.updateRekrutmen(idRekrutmen, rekrutmen);
            return ResponseEntity.ok().body(updatedRekrutmen);
        } catch(IllegalStateException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{idRekrutmen}")
    @ResponseBody
    public ResponseEntity<?> deleteRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen) {
        try {
            rekrutmenService.deleteRekrutmen(idRekrutmen);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
