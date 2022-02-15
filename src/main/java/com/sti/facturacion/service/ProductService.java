package com.sti.facturacion.service;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.exception.CustomerNotFoundException;
import com.sti.facturacion.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    ProductDto findProductById(final String productId) throws ProductNotFoundException;

    void deleteProductById(final String productId);

}
