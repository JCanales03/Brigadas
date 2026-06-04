package com.conaf.microservicio.brigadas.controller;

import com.conaf.microservicio.brigadas.dto.CreateBrigadaRequest;
import com.conaf.microservicio.brigadas.dto.UpdateBrigadaRequest;
import com.conaf.microservicio.brigadas.mapper.BrigadaMapper;
import com.conaf.microservicio.brigadas.model.Brigada;
import com.conaf.microservicio.brigadas.service.BrigadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brigadas")
public class BrigadaController {

    @Autowired
    private BrigadaService service;

    @GetMapping("/filtro/estado/{estadoBrigada}")
    public ResponseEntity<List<Brigada>> buscarPorEstado(@PathVariable String estadoBrigada) {
        return ResponseEntity.ok(service.getBrigadasPorEstado(estadoBrigada));
    }

    @GetMapping
    public ResponseEntity<List<Brigada>> getAll() {
        return ResponseEntity.ok(service.getBrigadas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brigada> getById(@PathVariable Long id) {
        Brigada brigada = service.getBrigadaId(id);
        if (brigada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brigada);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateBrigadaRequest request) {
        try {
            Brigada brigada = BrigadaMapper.toModel(request);
            return ResponseEntity.ok(service.saveBrigada(brigada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateBrigadaRequest request) {
        try {
            Brigada brigada = BrigadaMapper.toModel(id, request);
            return ResponseEntity.ok(service.updateBrigada(brigada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Brigada brigada = service.getBrigadaId(id);
        if (brigada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.deleteBrigada(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        service.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok("Estado actualizado correctamente");
    }
}
