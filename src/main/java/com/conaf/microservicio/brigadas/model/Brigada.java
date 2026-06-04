package com.conaf.microservicio.brigadas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brigadas")
public class Brigada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;           // Ej: "Brigada Terrestre Alfa", "Unidad Avión Cisterna 02"
    private String tipo;             // "Terrestre", "Avión Cisterna", "Helicoptero con Balde", "Camión Aljibe"
    private String region;           // Región donde opera
    private int cantidadMiembros;    // Cantidad de personas (Solo restrictivo para "Terrestre")
    private String estadoBrigada;           // "Disponible", "En Combate", "Desplegado"
}