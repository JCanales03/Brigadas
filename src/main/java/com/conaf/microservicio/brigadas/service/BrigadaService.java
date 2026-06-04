package com.conaf.microservicio.brigadas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.conaf.microservicio.brigadas.model.Brigada;
import com.conaf.microservicio.brigadas.repository.BrigadaRepository;
import com.conaf.microservicio.brigadas.exception.*;

@Service
public class BrigadaService {

    @Autowired
    private BrigadaRepository brigadaRepository;

    public List<Brigada> getBrigadas() {
        return brigadaRepository.findAll();
    }

    public Brigada getBrigadaId(Long id) {
        return brigadaRepository.findById(id).orElseThrow(() -> new BrigadaNotFoundException("No se encontró la brigada con ID: " + id));
    }

    public List<Brigada> getBrigadasPorEstado(String estadoBrigada) {
        return brigadaRepository.findByEstadoBrigada(estadoBrigada);
    }

    public Brigada saveBrigada(Brigada brigada) {
        validarReglasCONAF(brigada);
        return brigadaRepository.save(brigada);
    }

    public Brigada updateBrigada(Brigada brigada) {
        validarReglasCONAF(brigada);
        return brigadaRepository.save(brigada);
    }

    public String deleteBrigada(Long id) {
        brigadaRepository.deleteById(id);
        return "Brigada eliminada con éxito";
    }

    private void validarReglasCONAF(Brigada brigada) {
        String tipo = brigada.getTipo().trim(); //  trim() para eliminar espacios en blanco Ej : "terrestre " sea igual que "terrestre" y no lo rechace

        boolean tipoValido = tipo.equalsIgnoreCase("Terrestre") || 
                             tipo.equalsIgnoreCase("Avión Cisterna") || 
                             tipo.equalsIgnoreCase("Helicoptero con Balde") || 
                             tipo.equalsIgnoreCase("Camión Aljibe");
                             
        if (!tipoValido) {
            throw new IllegalArgumentException("Tipo de brigada/unidad inválido. Debe ser: Terrestre, Avión Cisterna, Helicoptero con Balde o Camión Aljibe.");
        }

        if (tipo.equalsIgnoreCase("Terrestre")) {
            if (brigada.getCantidadMiembros() < 10 || brigada.getCantidadMiembros() > 20) {
                throw new IllegalArgumentException("Las brigadas de tipo Terrestre deben ser cuadrillas de entre 10 y 20 personas.");
            }
        }
    }

    public void cambiarEstado(Long id, String nuevoEstado) {
        Brigada brigada = brigadaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Brigada no encontrada"));
        brigada.setEstadoBrigada(nuevoEstado);
        brigadaRepository.save(brigada);
    }
}
