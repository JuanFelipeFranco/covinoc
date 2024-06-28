package com.covinoc.test.dominio.servicio;

import com.covinoc.test.dominio.Producto;
import com.covinoc.test.dominio.repositorioDom.ProductoRepositorioDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {
    @Autowired
    private ProductoRepositorioDom productRepositoryDom;

    public List<Producto> obtenerProductos(){
        return productRepositoryDom.obtenerProductos();
    }

    public Optional<Producto> obtenerProductoPorId(int productoId){
        return productRepositoryDom.obtenerProductoPorId(productoId);
    }

    public  Optional<List<Producto>> obtenerProductosPorCategoria(int categoriaId){
        return productRepositoryDom.obtenerProductosPorCategoria(categoriaId);
    }

    public Producto guardar(Producto producto){
        return productRepositoryDom.guardar(producto);
    }

    public Producto actualizar(int productoId,Producto producto){
        return productRepositoryDom.actualizar(productoId,producto);
    }


    public boolean delete(int productoId){
        return obtenerProductoPorId(productoId).map(product -> {
            productRepositoryDom.delete(productoId);
            return true;
        }).orElse(false);
    }
}
