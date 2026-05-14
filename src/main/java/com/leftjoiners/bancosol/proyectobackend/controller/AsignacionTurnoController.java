/*
Javier Urbaneja Benítez: 100%
*/

package com.leftjoiners.bancosol.proyectobackend.controller;

import com.leftjoiners.bancosol.proyectobackend.dao.*;
import com.leftjoiners.bancosol.proyectobackend.dto.AsignacionTurno;
import com.leftjoiners.bancosol.proyectobackend.dto.Colaborador;
import com.leftjoiners.bancosol.proyectobackend.dto.TiendaCampanya;
import com.leftjoiners.bancosol.proyectobackend.dto.Turno;
import com.leftjoiners.bancosol.proyectobackend.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/turnos")
public class AsignacionTurnoController {
    private final TiendaCampanyaService tiendaCampanyaService;
    private final AsignacionTurnoService asignacionTurnoService;
    private final TurnoService turnoService;
    private final ColaboradorService colaboradorService;
    private final TipoTurnoService tipoTurnoService;

    @Autowired
    protected TipoTurnoRepository tipoTurnoRepository;

    @GetMapping("")
    public String doInit(Model model) {
        List<AsignacionTurno> asignacionColaboradores = asignacionTurnoService.listarAsignacionColaboradores();

        model.addAttribute("asignacionColaboradores", asignacionColaboradores);
        model.addAttribute("currentSection", "turnos");
        return "turnos/asignacion_turno";
    }

    @PostMapping("/buscarTurno")
    public String buscarTurno(@RequestParam(value = "id", required = false) Integer idTienda,
                              @RequestParam(value = "turno", required = false) Integer turno,
                              @RequestParam(value = "lineales", required = false) Integer lineales,
                              @RequestParam(value = "linealActual", required = false) Integer linealActual,
                              Model model) {

        Turno asignacionTurno = this.turnoService.buscarTurnoEspecifico(idTienda, turno, linealActual);
        TiendaCampanya tiendaCampanya = this.tiendaCampanyaService.buscarTiendaCampanya(idTienda);

        model.addAttribute("turno", turno);
        model.addAttribute("lineales", lineales);
        model.addAttribute("linealActual", linealActual);
        model.addAttribute("tienda", tiendaCampanya);
        model.addAttribute("asignacionTurno", asignacionTurno);

        return "turnos/info_turno";
    }

    @GetMapping("/crearTurno")
    public String crearTurno(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "turno", required = false) Integer turno,
                              @RequestParam(value = "lineal", required = false) Integer lineal,
                              Model model) {
        TiendaCampanya tienda = this.tiendaCampanyaService.buscarTiendaCampanya(id);
        Turno asignacionTurno = this.turnoService.buscarTurnoEspecifico(id, turno, lineal);
        Colaborador colaborador = null;

        if (asignacionTurno != null && asignacionTurno.getColaborador() != null) {
            colaborador = this.colaboradorService.buscarColaborador(asignacionTurno.getColaborador().getId());
        }

        if (asignacionTurno == null) {
            asignacionTurno = new Turno();
            asignacionTurno.setTiendaCampanya(tienda);
            asignacionTurno.setLineal(lineal);
            asignacionTurno.setTipoTurno(this.tipoTurnoService.buscarTipoTurno(turno));
        }

        model.addAttribute("colaboradores", this.colaboradorService.listarColaboradores());
        model.addAttribute("colaborador", colaborador);
        model.addAttribute("tienda", tienda.getTienda());
        model.addAttribute("asignacionTurno", asignacionTurno);
        model.addAttribute("currentSection", "turnos");
        return "turnos/formulario_turno";
    }

    @PostMapping("/guardarTurno")
    public String guardarTurno(@RequestParam(value = "id", required = false) Integer id,
                               @RequestParam("tiendaCampanyaId") Integer tiendaCampanyaId,
                               @RequestParam("tipoTurnoId") Integer tipoTurnoId,
                               @RequestParam("lineal") Integer lineal,
                               @RequestParam("idColaborador") Integer idColaborador,
                               @RequestParam("horaInicio") LocalTime horaInicio,
                               @RequestParam("horaFin") LocalTime horaFin,
                               @RequestParam("numVoluntarios") Integer numVoluntarios,
                               @RequestParam("observaciones") String observaciones) {
        this.turnoService.guardarTurno(
                id, tiendaCampanyaId, tipoTurnoId, lineal, idColaborador,
                horaInicio, horaFin, numVoluntarios, observaciones
        );

        return "redirect:/turnos";
    }

    @PostMapping("/buscarColaborador")
    public String buscarColaborador(@RequestParam("id") Integer id, Model model) {
        Colaborador colaborador = this.colaboradorService.buscarColaborador(id);

        model.addAttribute("colaborador", colaborador);

        return "colaboradores/info_colaboradores";
    }
}
