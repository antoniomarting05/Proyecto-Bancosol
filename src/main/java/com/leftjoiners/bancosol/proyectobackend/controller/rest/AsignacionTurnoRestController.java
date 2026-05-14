package com.leftjoiners.bancosol.proyectobackend.controller.rest;

import com.leftjoiners.bancosol.proyectobackend.dto.AsignacionTurno;
import com.leftjoiners.bancosol.proyectobackend.service.AsignacionTurnoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/asignacionTurnos")
public class AsignacionTurnoRestController {
    private final AsignacionTurnoService asignacionTurnoService;

    @GetMapping("/")
    public List<AsignacionTurno> doInit() {
        return this.asignacionTurnoService.listarAsignacionColaboradores();
    }
}
