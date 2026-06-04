package com.conaf.microservicio.brigadas.mapper;

import com.conaf.microservicio.brigadas.dto.CreateBrigadaRequest;
import com.conaf.microservicio.brigadas.dto.UpdateBrigadaRequest;
import com.conaf.microservicio.brigadas.model.Brigada;

public class BrigadaMapper {

    public static Brigada toModel(CreateBrigadaRequest request) {
        Brigada brigada = new Brigada();
        brigada.setNombre(request.nombre());
        brigada.setTipo(request.tipo());
        brigada.setRegion(request.region());
        brigada.setCantidadMiembros(request.cantidadMiembros());
        brigada.setEstadoBrigada(request.estadoBrigada());
        return brigada;
    }

    public static Brigada toModel(Long id, UpdateBrigadaRequest request) {
        Brigada brigada = new Brigada();
        brigada.setId(id);
        brigada.setNombre(request.nombre());
        brigada.setTipo(request.tipo());
        brigada.setRegion(request.region());
        brigada.setCantidadMiembros(request.cantidadMiembros());
        brigada.setEstadoBrigada(request.estadoBrigada());
        return brigada;
    }
}
