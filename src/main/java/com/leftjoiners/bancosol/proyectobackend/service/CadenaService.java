package com.leftjoiners.bancosol.proyectobackend.service;

import com.leftjoiners.bancosol.proyectobackend.dao.CadenaRepository;
import com.leftjoiners.bancosol.proyectobackend.dto.Cadena;
import com.leftjoiners.bancosol.proyectobackend.entity.CadenaEntity;
import com.leftjoiners.bancosol.proyectobackend.mapper.CadenaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CadenaService {
    private final CadenaRepository cadenaRepository;
    private final CadenaMapper cadenaMapper;

    public List<Cadena> listarCadenas() {
        List<CadenaEntity> cadenas = this.cadenaRepository.findAll();
        return this.cadenaMapper.toDTOList(cadenas);
    }
}
