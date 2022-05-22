package com.example.Sucursal.service;

import com.example.Sucursal.domain.Sucursal;
import com.example.Sucursal.proyeccion.GetDireccionDto;
import com.example.Sucursal.repository.SucursalRepo;

import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class SucursalServiceImpl implements SucursalService {




    private SucursalRepo sucursalRepo;

    public SucursalServiceImpl(SucursalRepo sucursalRepo) {
        this.sucursalRepo = sucursalRepo;
    }

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepo.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return sucursalRepo.findById(id);
    }

    @Override
    public Sucursal add(Sucursal sucursal) {
        return sucursalRepo.save(sucursal);
    }

    @Override
    public Optional<GetDireccionDto> distanciaCercana(Double inlatitud, Double inlongitud) {
        return sucursalRepo.findByDistanciaCercana(inlatitud,inlongitud).stream().findFirst();
    }

    public void eliminarPorIdSiExite(long id) {

            sucursalRepo.deleteById(id);
    }
}
