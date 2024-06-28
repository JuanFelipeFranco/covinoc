package com.covinoc.test.persistencia.mapper;

import com.covinoc.test.dominio.Producto;
import com.covinoc.test.persistencia.entidad.ProductoDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface ProductoMapper {
    @Mappings({
            @Mapping(source = "idProducto",target = "productoId"),
            @Mapping(source = "nombre",target = "renombre"),
            @Mapping(source = "idCategoria",target = "categoriaId"),
            @Mapping(source = "precioVenta",target = "precio"),
            @Mapping(source = "cantidadStock",target = "inventario"),
            @Mapping(source = "estado",target = "activo"),
            @Mapping(source = "categoriaDto",target = "categoria")
    })

    Producto toProducto(ProductoDto productoDto);
    List<Producto> toProductos(List<ProductoDto> productos);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras",ignore = true)
    ProductoDto toProductoDto(Producto producto);

}
