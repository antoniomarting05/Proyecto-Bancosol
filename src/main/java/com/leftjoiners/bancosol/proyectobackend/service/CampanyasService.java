package com.leftjoiners.bancosol.proyectobackend.service;


import com.leftjoiners.bancosol.proyectobackend.dao.CadenaRepository;
import com.leftjoiners.bancosol.proyectobackend.dao.CampanyaRepository;
import com.leftjoiners.bancosol.proyectobackend.dao.TipoCampanyasRepository;
import com.leftjoiners.bancosol.proyectobackend.dto.Campanya;
import com.leftjoiners.bancosol.proyectobackend.entity.CampanyaEntity;
import com.leftjoiners.bancosol.proyectobackend.mapper.CampanyaMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampanyasService {
    private final CampanyaRepository campanyaRepository;
    private final TipoCampanyasRepository tipoCampanyasRepository;
    private CadenaRepository cadenaRepository;
    private CampanyaMapper campanyaMapper;

    public List<Campanya> listarCampanyas() {
        List<CampanyaEntity> campanyas = this.campanyaRepository.findAll();
        return this.campanyaMapper.toDTOList(campanyas);
    }
}
