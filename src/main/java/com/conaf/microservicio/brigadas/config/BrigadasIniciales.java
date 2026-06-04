package com.conaf.microservicio.brigadas.config;

import com.conaf.microservicio.brigadas.model.Brigada;
import com.conaf.microservicio.brigadas.repository.BrigadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BrigadasIniciales implements CommandLineRunner {

    @Autowired
    private BrigadaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            
            repository.save(new Brigada(null, "Avión Cisterna 01", "Aéreo", "Talca - Foco Mayor", 2, "En Trayecto"));
            repository.save(new Brigada(null, "Camión Aljibe 04", "Terrestre", "Talca - Punto Abastecimiento Agua", 4, "En Trayecto"));
            repository.save(new Brigada(null, "Brigada Terrestre 03", "Terrestre", "Talca - Monte Nativo", 4, "Rota"));
        }
    }
}
