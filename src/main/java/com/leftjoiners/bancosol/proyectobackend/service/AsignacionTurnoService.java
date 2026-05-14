/*
Javier Urbaneja Benítez: 100%
*/

package com.leftjoiners.bancosol.proyectobackend.service;

import com.leftjoiners.bancosol.proyectobackend.dao.*;
import com.leftjoiners.bancosol.proyectobackend.dto.AsignacionTurno;
import com.leftjoiners.bancosol.proyectobackend.entity.AsignacionTurnoEntity;
import com.leftjoiners.bancosol.proyectobackend.mapper.AsignacionTurnoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignacionTurnoService {
    private final TiendaCampanyaRepository tiendaCampanyaRepository;
    private final AsignacionTurnoRepository asignacionTurnoRepository;
    private final TipoTurnoRepository tipoTurnoRepository;
    private final ColaboradoresRespository colaboradoresRespository;
    private final TurnoRepository turnoRepository;
    private final AsignacionTurnoMapper asignacionTurnoMapper;

    public List<AsignacionTurno> listarAsignacionColaboradores () {
        List<AsignacionTurnoEntity> asignacionColaboradores = asignacionTurnoRepository.findAll();
        return asignacionTurnoMapper.toDTOList(asignacionColaboradores);
    }
}
