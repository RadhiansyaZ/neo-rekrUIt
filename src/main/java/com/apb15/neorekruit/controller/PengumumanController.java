package com.apb15.neorekruit.controller;

import com.apb15.neorekruit.model.Pengumuman;
import com.apb15.neorekruit.service.PengumumanService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rekrutmen/{idRekrutmen}/pengumuman")
@RequiredArgsConstructor
public class PengumumanController {
    private final PengumumanService pengumumanService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> listAllPengumumanByRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen) {
        var allPengumumanByRekrutmen = pengumumanService.findAllPengumumanInRekrutmen(idRekrutmen);
        return ResponseEntity.ok().body(allPengumumanByRekrutmen);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Pengumuman> createPengumumanForRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen,
                                                          @RequestBody Pengumuman pengumuman) {
        try {
            var createdPengumuman = pengumumanService.createPengumuman(idRekrutmen, pengumuman);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(String.format("/%s/pengumuman", idRekrutmen))
                    .toUriString());

            return ResponseEntity.created(uri).body(createdPengumuman);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot create pengumuman", exception);
        }
    }

    @GetMapping("/{idPengumuman}")
    @ResponseBody
    public ResponseEntity<Pengumuman> getPengumumanForRekrutmenById(@PathVariable(value = "idRekrutmen") Long idRekrutmen,
                                                                   @PathVariable(value = "idPengumuman") Long idPengumuman) {
        try {
            var pengumuman = pengumumanService.findPengumumanById(idRekrutmen, idPengumuman);

            return ResponseEntity.ok().body(pengumuman);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idPengumuman}")
    @ResponseBody
    public ResponseEntity<Pengumuman> updatePengumumanForRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen,
                                                                   @PathVariable(value = "idPengumuman") Long idPengumuman,
                                                                   @RequestBody Pengumuman pengumuman) {
        try {
            var updatedPengumuman = pengumumanService.updatePengumuman(idRekrutmen, idPengumuman, pengumuman);

            return ResponseEntity.ok().body(updatedPengumuman);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idPengumuman}")
    @ResponseBody
    public ResponseEntity<?> deletePengumumanForRekrutmen(@PathVariable(value = "idRekrutmen") Long idRekrutmen,
                                                          @PathVariable(value = "idPengumuman") Long idPengumuman) {
        try {
            pengumumanService.deletePengumuman(idRekrutmen, idPengumuman);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
