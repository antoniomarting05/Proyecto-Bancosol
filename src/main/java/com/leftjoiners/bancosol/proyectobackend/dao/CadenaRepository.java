package com.leftjoiners.bancosol.proyectobackend.dao;

import com.leftjoiners.bancosol.proyectobackend.entity.CadenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadenaRepository extends JpaRepository<CadenaEntity,Integer> {
    Integer id(Integer id);
}
