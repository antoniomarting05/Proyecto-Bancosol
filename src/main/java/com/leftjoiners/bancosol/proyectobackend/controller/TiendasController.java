package com.leftjoiners.bancosol.proyectobackend.controller;

import com.leftjoiners.bancosol.proyectobackend.dao.*;
import com.leftjoiners.bancosol.proyectobackend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/tiendas")
@Controller
public class TiendasController {

    @Autowired
    protected TiendaRepository tiendaRepository;

    @Autowired
    protected TiendaCampanyaRepository tiendaCampanyaRepository;

    @Autowired
    protected CadenaRepository cadenaRepository;

    @Autowired
    protected ZonaRepository zonaRepository;

    @Autowired
    protected LocalidadRepository localidadRepository;

    //@Autowired
    //protected ColaboradoresRespository colaboradoresRespository;

    @GetMapping("")
    public String doTiendas(Model model,
                            @RequestParam(value = "tiendas", required = false) List<TiendaEntity> tiendasFiltradas) {

        List<TiendaEntity> tiendas = tiendaRepository.findAll();
        List<TiendaCampanyaEntity> tiendaCampanyas = tiendaCampanyaRepository.findAll();

        model.addAttribute("tiendas", tiendas);
        model.addAttribute("tiendaCampanyas", tiendaCampanyas);
        model.addAttribute("currentSection", "tiendas");

        //Filtrado:
        List<CadenaEntity> cadenas = cadenaRepository.findAll();
        model.addAttribute("cadenas", cadenas);
        List<ZonaEntity> zonas = zonaRepository.findAll();
        model.addAttribute("zonas", zonas);
        List<LocalidadEntity> localidades = localidadRepository.findAll();
        model.addAttribute("localidades", localidades);
        //List<ColaboradorEntity> colaboradores = colaboradoresRespository.findAll();
        //model.addAttribute("colaboradores", colaboradores);

        return "/tiendas/tiendas";
    }

    @PostMapping("/filtrarTiendas")
    public String doFiltrarTiendas(Model model,
                                   @RequestParam(value = "cadena-tienda", required = false) Integer cadenaId,
                                   @RequestParam(value = "localidad-tienda", required = false) Integer localidadId,
                                   @RequestParam(value = "zona-tienda", required = false) Integer zonaId){
                                   //@RequestParam("coordinador") Integer coordinador)


        List<TiendaEntity> tiendasFiltradas = this.tiendaRepository.filtrarTiendasMulticriterio(cadenaId, localidadId /*, zonaId*/);
        model.addAttribute("tiendas", tiendasFiltradas);

        model.addAttribute("tiendaCampanyas", this.tiendaCampanyaRepository.findAll());
        model.addAttribute("cadenas", this.cadenaRepository.findAll());
        model.addAttribute("zonas", this.zonaRepository.findAll());
        model.addAttribute("localidades", this.localidadRepository.findAll());

        return "/tiendas/tiendas";
    }
}
