package com.leftjoiners.bancosol.proyectobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Integer id;
    private Rol rol;
    private String usuario;
    private String contrasenya;
    private String nombre;
    private String telefono;
    private String email;
    private Entidad entidad;
    private Zona zonaAsignada;
    private Localidad localidad;
    private Integer cp;
    private Distrito distrito;

    private List<Integer> colaboradoresCoordinados;
    private List<Integer> tiendasCoordinadas;
    private List<Integer> tiendasCapitaneadas;
}