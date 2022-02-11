package com.sti.facturacion.exception;

import com.sti.facturacion.model.Product;

/**
 * Product 404 status exception.
 * @author Laurent Geovanny Caceres
 */
public class ProductNotFoundException extends ResourceNotFoundException {

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildProductNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Product.class, field, fieldValue);
    }

    /**
     *
     * @param productId
     * @return
     */
    public static ResourceNotFoundException
    buildProductNotFoundExceptionForId(String productId){
        return resourceNotFoundExceptionOf(Product.class, "productId", productId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildProductNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Product.class, searchParams);
    }
}
