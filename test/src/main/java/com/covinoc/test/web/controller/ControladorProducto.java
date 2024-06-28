package com.covinoc.test.web.controller;

import com.covinoc.test.dominio.Producto;
import com.covinoc.test.dominio.servicio.ServicioProducto;
import com.covinoc.test.web.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Obtener todos los productos de la tienda")
    @ApiResponse(code=200, message = "Ok")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productos = servicioProducto.obtenerProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar un producto con el ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Ok"),
            @ApiResponse(code=404, message = "Producto NO encontrado"),
    })
    public ResponseEntity<Producto> obtenerProductoPorId(@ApiParam(value = "El id del producto", required = true, example = "7")
                                                             @PathVariable("id") int productoId) {
        Optional<Producto> producto = servicioProducto.obtenerProductoPorId(productoId);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/categoria/{categoriaId}")
    @ApiOperation(value = "Obtener los Productos de la tienda por el id de la categoria")
    @ApiResponses({
            @ApiResponse(code=200, message = "Ok"),
            @ApiResponse(code=404, message = "Producto NO encontrado"),
    })
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@ApiParam(value = "El id de la categoria", required = true, example = "2")
            @PathVariable("categoriaId") int categoriaId) {
        Optional<List<Producto>> productos = servicioProducto.obtenerProductosPorCategoria(categoriaId);
        return productos.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/guardar")
    @ApiOperation(value = "Guardar un producto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREADO"),
            @ApiResponse(code= 400, message = "Solicitud Incorrecta"),
    })
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        Producto savedProducto = servicioProducto.guardar(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    @ApiOperation(value = "Actualizar un producto por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Producto.class),
            @ApiResponse(code = 404, message = "Producto no encontrado")
    })
    public ResponseEntity<Producto> actualizarProducto(@ApiParam(value = "ID del producto a actualizar", required = true)
            @PathVariable int id, @RequestBody Producto producto) {
        try {
            Producto updatedProducto = servicioProducto.actualizar(id, producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminar un producto por el Id")
    @ApiResponses({
            @ApiResponse(code=200, message = "Ok"),
            @ApiResponse(code=404, message = "Producto NO encontrado"),
    })
    public ResponseEntity<Void> delete(@ApiParam(value = "El id del producto a eliminar", required = true, example = "6")
            @PathVariable("id") int productId) {
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
