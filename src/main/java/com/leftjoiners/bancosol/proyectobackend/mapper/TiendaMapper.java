/*
Javier Urbaneja Benítez 80%
IA: 20%
*/

package com.leftjoiners.bancosol.proyectobackend.mapper;

import com.leftjoiners.bancosol.proyectobackend.dto.Cadena;
import com.leftjoiners.bancosol.proyectobackend.dto.Distrito;
import com.leftjoiners.bancosol.proyectobackend.dto.Localidad;
import com.leftjoiners.bancosol.proyectobackend.dto.Tienda;
import com.leftjoiners.bancosol.proyectobackend.entity.TiendaEntity;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class TiendaMapper extends MapperDTO<Tienda, TiendaEntity> {

    @Override
    public Tienda toDTO(TiendaEntity entity) {
        if (entity == null) return null;

        Tienda dto = new Tienda();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setLineales(entity.getLineales());
        dto.setCp(entity.getCp());
        dto.setDomicilio(entity.getDomicilio());

        if (entity.getCadena() != null) {
            Cadena c = new Cadena();
            c.setId(entity.getCadena().getId());
            c.setNombre(entity.getCadena().getNombre());
            dto.setCadena(c);
        }

        if (entity.getLocalidad() != null) {
            Localidad l = new Localidad();
            l.setId(entity.getLocalidad().getId());
            l.setNombre(entity.getLocalidad().getNombre());
            dto.setLocalidad(l);
        }

        if (entity.getDistrito() != null) {
            Distrito d = new Distrito();
            d.setId(entity.getDistrito().getId());
            d.setNombre(entity.getDistrito().getNombre());
            dto.setDistrito(d);
        }

        if (entity.getTiendasCampanya() != null) {
            dto.setTiendasCampanya(entity.getTiendasCampanya().stream()
                    .map(tc -> tc.getId()).collect(Collectors.toList()));
        }

        return dto;
    }
}