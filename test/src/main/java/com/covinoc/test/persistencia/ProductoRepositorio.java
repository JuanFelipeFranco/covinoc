package com.covinoc.test.persistencia;
import com.covinoc.test.dominio.Producto;
import com.covinoc.test.dominio.repositorioDom.ProductoRepositorioDom;
import com.covinoc.test.persistencia.entidad.ProductoDto;
import com.covinoc.test.persistencia.mapper.ProductoMapper;
import com.covinoc.test.persistencia.repositorio.ProductoCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepositorio implements ProductoRepositorioDom {
    @Autowired
    private ProductoCrudRepositorio productoCrudRepositorio;

    @Autowired
    private ProductoMapper mapper;

    @Override
    public List<Producto> obtenerProductos() {
        List<ProductoDto> productos = (List<ProductoDto>) productoCrudRepositorio.findAll();
        return mapper.toProductos(productos);
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(int productoId) {
        return productoCrudRepositorio.findById(productoId).map(producto -> mapper.toProducto(producto));
    }

    @Override
    public Optional<List<Producto>> obtenerProductosPorCategoria(int categoriaId) {
        List<ProductoDto> productos = productoCrudRepositorio.findByIdCategoriaOrderByNombreAsc(categoriaId);
        return Optional.of(mapper.toProductos(productos));
    }

    @Override
    public Producto guardar(Producto producto) {
        ProductoDto productoDto = mapper.toProductoDto(producto);
        return mapper.toProducto(productoCrudRepositorio.save(productoDto));
    }

    @Override
    public Producto actualizar(int productoId, Producto producto) {
        if (productoCrudRepositorio.existsById(productoId)) {
            ProductoDto productoDto = mapper.toProductoDto(producto);
            productoDto.setIdProducto(productoId);
            return mapper.toProducto(productoCrudRepositorio.save(productoDto));
        } else {
            return null;
        }
    }

    @Override
    public void delete(int productoId) {
        productoCrudRepositorio.deleteById(productoId);
    }
}
