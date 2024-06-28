package com.covinoc.test.persistencia.mapper;

import com.covinoc.test.dominio.Categoria;
import com.covinoc.test.persistencia.entidad.CategoriaDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    @Mappings({
            @Mapping(source = "idCategoria",target = "categoriaId"),
            @Mapping(source = "descripcion",target = "definicion"),
            @Mapping(source = "estado",target = "activo"),
    })
    Categoria toCategoria(CategoriaDto categoriaDto);

    @InheritInverseConfiguration
    @Mapping(target = "productos",ignore = true)
    CategoriaDto toCategoriaDto(Categoria categoria);
}
