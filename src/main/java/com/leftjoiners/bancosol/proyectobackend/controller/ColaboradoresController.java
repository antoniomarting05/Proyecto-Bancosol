package com.leftjoiners.bancosol.proyectobackend.controller;

import com.leftjoiners.bancosol.proyectobackend.dao.ColaboradoresRespository;
import com.leftjoiners.bancosol.proyectobackend.entity.ColaboradorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("/colaboradores")
@Controller
public class ColaboradoresController {
    @Autowired
    protected ColaboradoresRespository colaboradoresRespository;


    @GetMapping("")
    public String doInit(Model model) {
        List<ColaboradorEntity> colaboradores = colaboradoresRespository.findAll();

        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("currentSection", "colaboradores");
        return "colaboradores/colaboradores";
    }

    @PostMapping("/buscarColaborador")
    public String doBuscar(@RequestParam("id") Integer id,
            Model model){

        ColaboradorEntity colaborador = colaboradoresRespository.findById(id).get();
        model.addAttribute("colaborador", colaborador);
        return "colaboradores/info_colaboradores";
    }
}
