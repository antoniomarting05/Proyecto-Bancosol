package com.leftjoiners.bancosol.proyectobackend.dao;

import com.leftjoiners.bancosol.proyectobackend.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradoresRespository  extends JpaRepository<ColaboradorEntity, Integer> {
}
