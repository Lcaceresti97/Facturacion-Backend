package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToProduct(ProductDto dto);

    ProductDto productToDto(Product product);

    List<Product> dtoToProduct(List<ProductDto> dtos);

    List<ProductDto> productTodto(List<Product> products);
}
