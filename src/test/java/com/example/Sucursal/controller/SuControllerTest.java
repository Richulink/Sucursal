package com.example.Sucursal.controller;

import com.example.Sucursal.domain.Sucursal;
import com.example.Sucursal.repository.SucursalRepo;
import com.example.Sucursal.service.SucursalServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SuControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    SucursalRepo sucursalRepo;

    @Autowired
    SucursalServiceImpl sucursalesServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void crearSucursal() throws Exception {

        Sucursal crear_sucursal =
                new Sucursal(null,"calle 26 n°3344","8:00/12:00_16:00/20:00",5.432,5.4324);


        ResultActions response = mockMvc.perform(post("/crear_sucursal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crear_sucursal)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.direccion",
                        is(crear_sucursal.getDireccion())))
                .andExpect(jsonPath("$.latitud",
                        is(crear_sucursal.getLatitud())))
                .andExpect(jsonPath("$.longitud",
                        is(crear_sucursal.getLongitud())))
                .andExpect(jsonPath("$.horarioDeAtencion",
                        is(crear_sucursal.getHorarioDeAtencion())));
    }

    @Test
    public void distanciaCercana() throws Exception{



        List<Sucursal> listaDeSucursales = new ArrayList<>();
        listaDeSucursales.add(Sucursal.builder()
                //.id(1L)
                .horarioDeAtencion("8:00/12:00_16:00/20:00")
                .direccion("calle 28_n°9347")
                .latitud(1.456)
                .longitud(1.4545)
                .build());
        listaDeSucursales.add(Sucursal.builder()
                .horarioDeAtencion("8:00/12:00_16:00/20:00")
                .direccion("calle 46_n°6574")
                .latitud(3.843847)
                .longitud(4.87574)
                .build());
        listaDeSucursales.add(Sucursal.builder()
                .horarioDeAtencion("8:00/12:00_16:00/20:00")
                .direccion("calle 22_n°4546")
                .latitud(2.45424)
                .longitud(2.4246)
                .build());

        sucursalRepo.saveAll(listaDeSucursales);

        ResultActions response = mockMvc.perform(get("/{longitud}/{latitud}", 2.000,2.000));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.horario_de_atencion", is("8:00/12:00_16:00/20:00")))
                .andExpect(jsonPath("$.direccion", is("calle 44_n°6787")));
    }



    // caso de prueba sin exito con creacion de registro nuevo


    /*
     @Test
    void crearSucursal() throws Exception {

        Sucursal crear_sucursal =
                new Sucursal(null,"calle 26 n°3344","8:00/12:00_16:00/20:00",5.432,5.4324);


        ResultActions response = mockMvc.perform(post("/crear_sucursal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crear_sucursal)));

        response.andDo(print()).
                andExpect(status().isCreated())
                // interviene  el ID que hace la modificacón del registro y no la creacion
                .andExpect(jsonPath("$.id",
                        is(crear_sucursal.getId())))
                .andExpect(jsonPath("$.direccion",
                        is(crear_sucursal.getDireccion())))
                .andExpect(jsonPath("$.latitud",
                        is(crear_sucursal.getLatitud())))
                .andExpect(jsonPath("$.longitud",
                        is(crear_sucursal.getLongitud())))
                .andExpect(jsonPath("$.horarioDeAtencion",
                        is(crear_sucursal.getHorarioDeAtencion())));
    }
     */
}

