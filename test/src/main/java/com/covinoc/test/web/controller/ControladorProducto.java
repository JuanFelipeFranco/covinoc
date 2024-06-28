package com.covinoc.test.web.controller;

import com.covinoc.test.dominio.Producto;
import com.covinoc.test.dominio.servicio.ServicioProducto;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ControladorProducto {
    @Autowired
    private ServicioProducto servicioProducto;

    @GetMapping()
    public List<Producto> obtenerProductos(){
        return servicioProducto.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable("id") int productoId){
        return servicioProducto.obtenerProductoPorId(productoId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public Optional<List<Producto>> obtenerProductosPorCategoria( @PathVariable("categoriaId") int categoriaId){
        return servicioProducto.obtenerProductosPorCategoria(categoriaId);

    }

    @PostMapping("/guardar")
    public Producto save(@RequestBody Producto producto){
        return servicioProducto.guardar(producto);
    }

    @PutMapping("/actualizar/{id}")
    public Producto actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        return servicioProducto.actualizar(id,producto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
       return servicioProducto.delete(productId);
    }
}
