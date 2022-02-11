package com.sti.facturacion.exception;

import com.sti.facturacion.model.Customer;

/**
 * Customer 404 status exception.
 * @author Laurent Geovanny Caceres
 */
public class CustomerNotFoundException extends ResourceNotFoundException{

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildCustomerNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Customer.class, field, fieldValue);
    }

    /**
     *
     * @param customerId
     * @return
     */
    public static ResourceNotFoundException
    buildCustomerNotFoundExceptionForId(String customerId){
        return resourceNotFoundExceptionOf(Customer.class, "customerId", customerId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildCustomerNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Customer.class, searchParams);
    }
}
