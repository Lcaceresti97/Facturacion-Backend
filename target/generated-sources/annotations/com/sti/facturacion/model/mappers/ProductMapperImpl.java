package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.dto.ProductDto.ProductDtoBuilder;
import com.sti.facturacion.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-15T21:41:37-0600",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToProduct(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductStatus( dto.getProductStatus() );
        product.setProductName( dto.getProductName() );
        product.setProductStock( dto.getProductStock() );

        return product;
    }

    @Override
    public ProductDto productToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productId( product.getProductId() );
        productDto.productName( product.getProductName() );
        productDto.productStock( product.getProductStock() );
        productDto.productStatus( product.getProductStatus() );

        return productDto.build();
    }

    @Override
    public List<Product> dtoToProduct(List<ProductDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( dtos.size() );
        for ( ProductDto productDto : dtos ) {
            list.add( dtoToProduct( productDto ) );
        }

        return list;
    }

    @Override
    public List<ProductDto> productTodto(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( productToDto( product ) );
        }

        return list;
    }
}
