package com.covinoc.test.dominio.repositorioDom;

import com.covinoc.test.dominio.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositorioDom {
    List<Producto> obtenerProductos();
    Optional<Producto> obtenerProductoPorId(int productoId);
    Optional<List<Producto>> obtenerProductosPorCategoria(int categoriaId);
    Producto guardar(Producto producto);
    Producto actualizar(int productoId, Producto producto);
    void delete(int productoId);

}
