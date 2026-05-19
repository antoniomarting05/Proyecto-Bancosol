package com.leftjoiners.bancosol.proyectobackend.controller.rest;

import com.leftjoiners.bancosol.proyectobackend.dto.TiendaCampanya;
import com.leftjoiners.bancosol.proyectobackend.service.TiendaCampanyaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/tiendas")
public class TiendasRestController {
    private final TiendaCampanyaService tiendaCampanyaService;

    @GetMapping("/buscarTiendaCampanya/{idTienda}")
    public TiendaCampanya buscarTiendaCampanya(@PathVariable Integer idTienda) {
        return this.tiendaCampanyaService.buscarTiendaCampanya(idTienda);
    }
}
