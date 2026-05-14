/*
Javier Urbaneja Benítez: 100%
*/

package com.leftjoiners.bancosol.proyectobackend.service;

import com.leftjoiners.bancosol.proyectobackend.dao.TiendaCampanyaRepository;
import com.leftjoiners.bancosol.proyectobackend.dto.TiendaCampanya;
import com.leftjoiners.bancosol.proyectobackend.entity.TiendaCampanyaEntity;
import com.leftjoiners.bancosol.proyectobackend.mapper.TiendaCampanyaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TiendaCampanyaService {
    private final TiendaCampanyaRepository tiendaCampanyaRepository;
    private final TiendaCampanyaMapper tiendaCampanyaMapper;

    public List<TiendaCampanya> listarTiendaCampanya () {
        List<TiendaCampanyaEntity> tiendaCampanyas = this.tiendaCampanyaRepository.findAll();
        return tiendaCampanyaMapper.toDTOList(tiendaCampanyas);
    }

    public TiendaCampanya buscarTiendaCampanya (Integer id) {
        if (id == null) return null;

        TiendaCampanyaEntity tiendaCampanya = this.tiendaCampanyaRepository.findById(id).orElse(null);

        return tiendaCampanyaMapper.toDTO(tiendaCampanya);
    }
}
