package com.intraConnect.intraConnect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;
    private String marca;
    private String especificaciones;
    private Integer cantidadMaxima;
    private Integer existencia;
    private Date fechaSalida;
    private Date fechaRegistro;

}
