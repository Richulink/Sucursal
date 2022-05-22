package com.example.Sucursal.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class implSQL {

    private final JdbcTemplate jdbcTemplate;

    public implSQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void sqlImplementation() throws Exception {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        jdbcTemplate.execute(DELETE_Sp_distancia_proxima_IF_EXITS);
        jdbcTemplate.execute(CREATE_SP_DISTANCIA_CERCANA);
        jdbcTemplate.execute(VALUES);
    }
    public static final String DELETE_Sp_distancia_proxima_IF_EXITS=
            "DROP PROCEDURE IF EXISTS Sp_distancia_proxima";

    public static final String CREATE_SP_DISTANCIA_CERCANA =
            "CREATE DEFINER=`root`@`localhost` PROCEDURE `Sp_distancia_proxima`(in inlatitud double,inlongitud double)\n" +
                    "BEGIN\n" +
                    " SELECT id,horario_de_atencion, direccion,         \n" +
                    "    abs(latitud - inlatitud) + abs(longitud - inlongitud) distancia\n" +
                    "    FROM sucursal order by distancia asc;\n" +
                    "END";


    public static final String VALUES =

            "INSERT INTO `sucursal` \n" +
                    "(`direccion`,`horario_de_atencion`,`latitud`,`longitud`) \n" +
                    "VALUES \n" +
                    "('calle 44_n°6787','8:00/12:00_16:00/20:00','1.78758','2.2228'),\n" +
                    "('calle 44_n°6787','8:00/12:00_16:00/20:00','4.9763','1.32123'),\n" +
                    "('calle 11_n°363','8:00/12:00_16:00/20:00','2.6363','1.9876'),\n" +
                    "('calle 11_n°363','8:00/12:00_16:00/20:00','8.78998','2.6445');\n";
}
