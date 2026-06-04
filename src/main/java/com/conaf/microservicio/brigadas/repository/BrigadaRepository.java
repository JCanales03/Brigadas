package com.conaf.microservicio.brigadas.repository;

import com.conaf.microservicio.brigadas.model.Brigada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BrigadaRepository extends JpaRepository<Brigada, Long> {
    List<Brigada> findByEstadoBrigada(String estadoBrigada);
}
