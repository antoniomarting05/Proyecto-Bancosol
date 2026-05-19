package com.leftjoiners.bancosol.proyectobackend.controller.rest;

import com.leftjoiners.bancosol.proyectobackend.dto.Campanya;
import com.leftjoiners.bancosol.proyectobackend.service.CampanyasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/campanyas")
public class CampanyaRestController {
    private final CampanyasService campanyasService;

    @GetMapping("/")
    public List<Campanya> doInit() {
        return this.campanyasService.listarCampanyas();
    }

    @GetMapping("/{id}")
    public Campanya buscarCampanya(@PathVariable Integer id) {
        return this.campanyasService.buscarCampanya(id);
    }

    public record CampanyaRequest(
            Integer id,
            String nombre,
            Integer idTipo,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            List<Integer> cadenasSeleccionadas
    ) {}

    @PostMapping("/guardar")
    public void guardarCampanya(@RequestBody CampanyaRequest request) {
        this.campanyasService.guardarCampanya(
                request.id(),
                request.nombre(),
                request.idTipo(),
                request.fechaInicio(),
                request.fechaFin(),
                request.cadenasSeleccionadas()
        );
    }

    @DeleteMapping("/eliminar")
    public void eliminarCampanyas(@RequestBody List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            this.campanyasService.eliminarCampanyas(ids);
        }
    }
}
