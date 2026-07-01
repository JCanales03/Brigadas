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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/brigadas")
@Tag(name = "Brigadas", description = "Gestión de brigadas de CONAF")
public class BrigadaController {

    @Autowired
    private BrigadaService service;

    @GetMapping("/filtro/estado/{estadoBrigada}")
    @Operation(summary = "Listar por estado", description = "Obtiene todas las brigadas según su estado actual")
    public ResponseEntity<List<Brigada>> buscarPorEstado(@PathVariable String estadoBrigada) {
        return ResponseEntity.ok(service.getBrigadasPorEstado(estadoBrigada));
    }

    @GetMapping
    @Operation(summary = "Listar todas", description = "Obtiene la lista completa de brigadas registradas")
    public ResponseEntity<List<Brigada>> getAll() {
        return ResponseEntity.ok(service.getBrigadas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Obtiene una brigada específica mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brigada encontrada"),
        @ApiResponse(responseCode = "404", description = "Brigada no encontrada")
    })
    public ResponseEntity<Brigada> getById(@PathVariable Long id) {
        Brigada brigada = service.getBrigadaId(id);
        if (brigada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brigada);
    }

    @PostMapping
    @Operation(summary = "Crear brigada", description = "Registra una nueva brigada en el sistema")
    public ResponseEntity<?> create(@Valid @RequestBody CreateBrigadaRequest request) {
        try {
            Brigada brigada = BrigadaMapper.toModel(request);
            return ResponseEntity.ok(service.saveBrigada(brigada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar brigada", description = "Modifica los datos de una brigada existente")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateBrigadaRequest request) {
        try {
            Brigada brigada = BrigadaMapper.toModel(id, request);
            return ResponseEntity.ok(service.updateBrigada(brigada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar brigada", description = "Borra una brigada del sistema por su ID")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Brigada brigada = service.getBrigadaId(id);
        if (brigada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.deleteBrigada(id));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado", description = "Actualiza solo el campo estado de la brigada")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        service.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok("Estado actualizado correctamente");
    }
}
