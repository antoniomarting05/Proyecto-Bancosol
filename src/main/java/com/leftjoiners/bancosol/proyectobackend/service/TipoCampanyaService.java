package com.leftjoiners.bancosol.proyectobackend.service;

import com.leftjoiners.bancosol.proyectobackend.dao.TipoCampanyasRepository;
import com.leftjoiners.bancosol.proyectobackend.dto.TipoCampanya;
import com.leftjoiners.bancosol.proyectobackend.entity.TipoCampanyaEntity;
import com.leftjoiners.bancosol.proyectobackend.mapper.TipoCampanyaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoCampanyaService {
    private final TipoCampanyasRepository tipoCampanyasRepository;
    private final TipoCampanyaMapper tipoCampanyaMapper;

    public List<TipoCampanya> listarTipoCampanyas() {
        List<TipoCampanyaEntity> tipoCampanyas = this.tipoCampanyasRepository.findAll();
        return this.tipoCampanyaMapper.toDTOList(tipoCampanyas);
    }
}
