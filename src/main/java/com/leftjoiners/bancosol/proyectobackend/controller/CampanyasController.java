package com.leftjoiners.bancosol.proyectobackend.controller;

import com.leftjoiners.bancosol.proyectobackend.dao.*;
import com.leftjoiners.bancosol.proyectobackend.entity.CadenaEntity;
import com.leftjoiners.bancosol.proyectobackend.entity.CampanyaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequestMapping("/campanyas")
@Controller
public class CampanyasController {

    @Autowired
    private CampanyaRepository campanyaRepo;

    @Autowired
    private TipoCampanyasRepository tipoCampanyaRepo;

    @Autowired
    private CadenaRepository cadenasRepo;

    @Autowired
    private TiendaRepository tiendaRepo;

    @Autowired
    private TiendaCampanyaRepository tiendaCampanyaRepo;

    @GetMapping("")
    public String listarCampanyas(Model model) {
        model.addAttribute("campanyas", campanyaRepo.findAll());
        model.addAttribute("currentSection", "campanyas");
        model.addAttribute("eliminar", false);
        return "campanyas/campanya";
    }

    @GetMapping("/crearCampanya")
    public String crearCampanya(Model model) {
        model.addAttribute("tiposCampanya", tipoCampanyaRepo.findAll());
        model.addAttribute("cadenas", cadenasRepo.findAll());
        model.addAttribute("editando", false);
        model.addAttribute("currentSection", "campanyas");
        return "campanyas/formularioCampanya";
    }

    @PostMapping("/guardarCampanya")
    public String guardarCampanya(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("nombreCampanya") String nombre,
            @RequestParam("tipoCampanya") Integer idTipo,
            @RequestParam("fechaInicio") LocalDate fechaInicio,
            @RequestParam("fechaFin") LocalDate fechaFin,
            @RequestParam(value = "cadenaParticipa", required = false) List<Integer> cadenasSeleccionadas
            ) {

        CampanyaEntity campanya;

        if (id != null) {
            campanya = campanyaRepo.findById(id).get();
            campanya.setId(id);
        } else {
            campanya = new CampanyaEntity();
        }

        campanya.setNombre(nombre);
        campanya.setTipoCampanya(tipoCampanyaRepo.findById(idTipo).get());
        campanya.setFechaInicio(fechaInicio);
        campanya.setFechaFin(fechaFin);
        if (cadenasSeleccionadas != null){
            campanya.setCadenasParticipantes(cadenasRepo.findAllById(cadenasSeleccionadas));
        }


        int duracion = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
        campanya.setDuracion(duracion);

        campanyaRepo.save(campanya);
        return "redirect:/campanyas";
    }



    @GetMapping("/editarCampanya")
    public String editarCampanya(@RequestParam("id") Integer id, Model model) {
        CampanyaEntity campanya = campanyaRepo.findById(id).get();
        if (campanya == null) {
            return "redirect:/campanyas";
        }
        model.addAttribute("currentSection", "campanyas");

        //VAlores de la campañana que estamos editando.
        model.addAttribute("nombreCampanya", campanya.getNombre());
        model.addAttribute("idCampanya", campanya.getId());
        model.addAttribute("fechaInicio", campanya.getFechaInicio());
        model.addAttribute("fechaFin", campanya.getFechaFin());
        model.addAttribute("tipoCampanyaActual", campanya.getTipoCampanya());

        List<CadenaEntity> cadenasCampanya = campanya.getCadenasParticipantes();
        model.addAttribute("cadenasCampanyaActual", cadenasCampanya);
        model.addAttribute("editando", true);

        //Valores genericos
        model.addAttribute("tiposCampanya", tipoCampanyaRepo.findAll());
        model.addAttribute("cadenas", cadenasRepo.findAll());

        model.addAttribute("currentSection", "campanyas");

        return "campanyas/formularioCampanya";
    }

    @GetMapping("/seleccionCampanyasEliminar")
    public String seleccionarCampanyasEliminar(Model model){

        model.addAttribute("campanyas", campanyaRepo.findAll());
        model.addAttribute("currentSection", "campanyas");
        model.addAttribute("eliminar", true);
        return "campanyas/campanya";
    }

    @PostMapping("/eliminarCampanyas")
    public String eliminarCampanyas(Model model,
                                  @RequestParam(required = false, value="campanyasElim") List<Integer> idCampanyasEliminar
                                  ){
        if(idCampanyasEliminar!=null){
            campanyaRepo.deleteAllById(idCampanyasEliminar);
        } else {
            return "redirect:/campanyas";
        }


        model.addAttribute("eliminar", false);
        return "redirect:/campanyas";

    }

    // ==========




    @GetMapping("/gestionarCadenas")
    public String gestionarCadenas(Model model){
        List<CadenaEntity> listaCadenas = cadenasRepo.findAll();

        model.addAttribute("cadenasSistema", listaCadenas);
        model.addAttribute("currentSection", "campanyas");
        model.addAttribute("eliminar", false);
        return "campanyas/cadenas";
    }

    @GetMapping("/seleccionCadenasEliminar")
    public String seleccionarCadenasEliminar(Model model){
        List<CadenaEntity> listaCadenas = cadenasRepo.findAll();

        model.addAttribute("cadenasSistema", listaCadenas);
        model.addAttribute("currentSection", "campanyas");
        model.addAttribute("eliminar", true);
        return "campanyas/cadenas";
    }

    @GetMapping("/crearCadena")
    public String crearCadena(Model model){
        model.addAttribute("editando", false);
        return "campanyas/formularioCadena";
    }

    @GetMapping("/editarCadena")
    public String editarCadena(Model model,
                               @RequestParam("id")Integer idCadena
                                ){
        CadenaEntity cadenaActual = cadenasRepo.findById(idCadena).get();

        model.addAttribute("nombreCadena", cadenaActual.getNombre());
        model.addAttribute("codigoCadena", cadenaActual.getCodigo());
        model.addAttribute("editando", true);
        model.addAttribute("idCadena", idCadena);
        model.addAttribute("currentSection", "campanyas");

        return "/campanyas/formularioCadena";

    }

    @PostMapping("/guardarCadena")
    public String guardarCadena(@RequestParam("nombre") String nombreCadena,
                                @RequestParam("codigo") String codigoCadena,
                                @RequestParam(required = false, value = "id" ) Integer idCadena
                                ){
        CadenaEntity cadenaActual;
        if (idCadena != null){
             cadenaActual = cadenasRepo.findById(idCadena).get();
        } else {
            cadenaActual = new CadenaEntity();
        }

        cadenaActual.setNombre(nombreCadena);
        cadenaActual.setCodigo(codigoCadena);
        cadenasRepo.save(cadenaActual);
        return "redirect:/campanyas/gestionarCadenas";
    }

    @PostMapping("/eliminarCadenasSistema")
    public String guardarCadenas (@RequestParam(required = false, value = "cadenas") List<Integer> idCadenasEliminar){

        if(idCadenasEliminar != null){
            cadenasRepo.deleteAllById(idCadenasEliminar);
        }

        return "redirect:/campanyas/gestionarCadenas";
    }

}