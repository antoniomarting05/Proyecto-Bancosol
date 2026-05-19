package com.leftjoiners.bancosol.proyectobackend.controller.rest;

import com.leftjoiners.bancosol.proyectobackend.dto.Cadena;
import com.leftjoiners.bancosol.proyectobackend.service.CadenaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/cadenas")
public class CadenaRestController {
    private final CadenaService cadenaService;

    @GetMapping("/")
    public List<Cadena> doInit() {
        return this.cadenaService.listarCadenas();
    }

    @GetMapping("/{id}")
    public Cadena buscarCadena(@PathVariable Integer id) {
        return this.cadenaService.buscarCadena(id);
    }

    public record CadenaRequest(
            Integer id,
            String nombre,
            String codigo
    ) {}

    @PostMapping("/guardar")
    public void guardarCadena(@RequestBody CadenaRequest request) {
        this.cadenaService.guardarCadena(
                request.id(),
                request.nombre(),
                request.codigo()
        );
    }

    @DeleteMapping("/eliminar")
    public void eliminarCadenas(@RequestBody List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            this.cadenaService.eliminarCadenas(ids);
        }
    }
}