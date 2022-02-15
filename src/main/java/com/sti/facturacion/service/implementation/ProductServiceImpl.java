package com.sti.facturacion.service.implementation;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.exception.CustomerNotFoundException;
import com.sti.facturacion.exception.ProductNotFoundException;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Product;
import com.sti.facturacion.model.mappers.InvoiceDetailsMapper;
import com.sti.facturacion.model.mappers.ProductMapper;
import com.sti.facturacion.model.status.ModelStatus;
import com.sti.facturacion.repository.InvoiceDetailsRepository;
import com.sti.facturacion.repository.ProductRepository;
import com.sti.facturacion.service.ProductService;
import com.sti.facturacion.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = Product.buildFromDtoProduct(this.productMapper.dtoToProduct(productDto));
        this.productRepository.save(product);
        return productMapper.productToDto(product);
    }

    @Override
    public ProductDto findProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> ProductNotFoundException.buildProductNotFoundExceptionForId(productId));
        return productMapper.productToDto(isActiveProduct(product,"productId",productId));
    }


    @Override
    public void deleteProductById(String productId) {
        Product product = productMapper.dtoToProduct(findProductById(productId));
        product.setProductStatus(ModelStatus.INACTIVE);
        productRepository.save(product);
    }

    /**
     * Return product if status code is ACTIVE.
     * @param product Product
     * @param queryField String
     * @param queryFieldValue String
     * @return Product
     * @throws ProductNotFoundException ex
     */
    private Product isActiveProduct(Product product, String queryField, String queryFieldValue){
        if(product.getProductStatus().getStatusCode() == 0){
            return product;
        }
        throw ProductNotFoundException
                .buildProductNotFoundExceptionForField(queryField, queryFieldValue);
    }
}
