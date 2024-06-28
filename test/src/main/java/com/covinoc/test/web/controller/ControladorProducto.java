package com.covinoc.test.web.controller;

import com.covinoc.test.dominio.Producto;
import com.covinoc.test.dominio.servicio.ServicioProducto;
import com.covinoc.test.web.exception.ResourceNotFoundException;
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
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productos = servicioProducto.obtenerProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") int productoId) {
        Optional<Producto> producto = servicioProducto.obtenerProductoPorId(productoId);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable("categoriaId") int categoriaId) {
        Optional<List<Producto>> productos = servicioProducto.obtenerProductosPorCategoria(categoriaId);
        return productos.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        Producto savedProducto = servicioProducto.guardar(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        try {
            Producto updatedProducto = servicioProducto.actualizar(id, producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int productId) {
        boolean deleted = servicioProducto.delete(productId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Manejo de excepciones
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
