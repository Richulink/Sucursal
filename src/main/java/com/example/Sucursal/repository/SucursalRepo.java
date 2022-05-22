package com.example.Sucursal.repository;


import com.example.Sucursal.domain.Sucursal;
import com.example.Sucursal.proyeccion.GetDireccionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SucursalRepo extends JpaRepository<Sucursal,Long> {

    @Query(value = "call sucursal.Sp_distancia_proxima(:inlatitud,:inlongitud);", nativeQuery = true)
    List<GetDireccionDto> findByDistanciaCercana (@Param ("inlatitud")Double inlatitud, @Param ("inlongitud")Double inlongitud);

}
