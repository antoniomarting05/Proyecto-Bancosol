package com.leftjoiners.bancosol.proyectobackend.controller.rest;

import com.leftjoiners.bancosol.proyectobackend.dto.Colaborador;
import com.leftjoiners.bancosol.proyectobackend.service.ColaboradorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/colaboradores")
public class ColaboradoresRestController {
    private final ColaboradorService colaboradorService;

    @GetMapping("/")
    public List<Colaborador> doInit() {
        return this.colaboradorService.listarColaboradores();
    }
}
