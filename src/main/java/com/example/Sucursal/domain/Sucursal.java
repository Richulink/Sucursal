package com.example.Sucursal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Builder
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String direccion;

    String horarioDeAtencion;

    Double latitud;

    Double longitud;

}
