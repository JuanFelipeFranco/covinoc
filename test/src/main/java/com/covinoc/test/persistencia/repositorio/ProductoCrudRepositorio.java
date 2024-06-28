package com.covinoc.test.persistencia.repositorio;

import com.covinoc.test.persistencia.entidad.ProductoDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoCrudRepositorio extends CrudRepository<ProductoDto, Integer> {
    List<ProductoDto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
}
