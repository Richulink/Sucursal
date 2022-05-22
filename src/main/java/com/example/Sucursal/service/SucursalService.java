package com.example.Sucursal.service;


import com.example.Sucursal.domain.Sucursal;
import com.example.Sucursal.proyeccion.GetDireccionDto;

import java.util.List;
import java.util.Optional;

public interface SucursalService {

    List<Sucursal> findAll();
    Optional findById(Long id);

    Sucursal add(Sucursal sucursal);

    Optional<GetDireccionDto> distanciaCercana(Double inlatitud, Double inlongitud);
}
