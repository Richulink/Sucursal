package com.example.Sucursal.controller;

import com.example.Sucursal.domain.Sucursal;
import com.example.Sucursal.service.SucursalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value = "/")
public class SuController {


    private static final Logger logger = LoggerFactory.getLogger(SuController.class);

    private SucursalServiceImpl sucursalesServiceImpl;

    public SuController(SucursalServiceImpl sucursalesServiceImpl) {
        this.sucursalesServiceImpl = sucursalesServiceImpl;
    }

    @GetMapping("/{latitud}/{longitud}")
    ResponseEntity<Sucursal> calculadorDeDistancia(@PathVariable("latitud") Double inlatitud, @PathVariable("longitud") Double inlongitud) {

        try {
             Optional sucursalCercana = sucursalesServiceImpl.distanciaCercana(inlatitud, inlongitud);
             logger.info("comparando valores de laitud y longitud");

            return new ResponseEntity(sucursalCercana, HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e.toString(),e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/buscar_por_id/{id}")
    ResponseEntity<Sucursal> buscarPorId(@PathVariable("id") Long id) {

            Optional<Sucursal> buscarId = sucursalesServiceImpl.findById(id);
            if (buscarId.isPresent()) {
                return new ResponseEntity(buscarId, HttpStatus.OK);
            } else {
                String mensaje = "no existe tal registro con ese id";
                return new ResponseEntity(mensaje, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/modificar_sucursal/{id}")
    public ResponseEntity<Sucursal> crearSucursal(@PathVariable("id") long id, @RequestBody Sucursal mod_Sucursal) {

        Optional<Sucursal> modificarPorId = sucursalesServiceImpl.findById(id);

        if (modificarPorId.isPresent()) {

            Sucursal sucursal = modificarPorId.get();
            sucursal.setDireccion(mod_Sucursal.getDireccion());
            sucursal.setHorarioDeAtencion(mod_Sucursal.getHorarioDeAtencion());
            sucursal.setLatitud(mod_Sucursal.getLatitud());
            sucursal.setLongitud(mod_Sucursal.getLongitud());
            sucursalesServiceImpl.add(sucursal);
            logger.info("modificando registro");
            return new ResponseEntity<>(sucursal, HttpStatus.CREATED);
        }else {
            String mensaje = "no existe tal registro con ese id";
            return new ResponseEntity(mensaje, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear_sucursal")
    public ResponseEntity<Sucursal> modificarSucursal(@RequestBody Sucursal nuevaSucursal) {
        try {
            Sucursal sucursal = sucursalesServiceImpl.add(nuevaSucursal);
            logger.info("creando nueva sucursal");
            return new ResponseEntity<>(sucursal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/eliminar-por-id/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        Optional resp = sucursalesServiceImpl.findById(id);
            if(resp.isPresent()){
                sucursalesServiceImpl. eliminarPorIdSiExite(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                String mensaje = "no existe tal registro";
            return new ResponseEntity(mensaje,HttpStatus.NOT_FOUND);
        }
    }
}