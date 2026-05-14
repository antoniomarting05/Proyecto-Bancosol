package com.leftjoiners.bancosol.proyectobackend.dao;

import com.leftjoiners.bancosol.proyectobackend.entity.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TurnoRepository extends JpaRepository<TurnoEntity, Integer> {
    @Query("SELECT a FROM TurnoEntity a " +
            "WHERE a.tiendaCampanya.id = :id " +
            "AND a.tipoTurno.id = :turno " +
            "AND (a.lineal = :linealActual OR a.lineal IS NULL)")
    public Optional<TurnoEntity> buscarTurnoEspecifico(@Param("id") Integer id,
                                                       @Param("turno") Integer turno,
                                                       @Param("linealActual") Integer linealActual);

}
