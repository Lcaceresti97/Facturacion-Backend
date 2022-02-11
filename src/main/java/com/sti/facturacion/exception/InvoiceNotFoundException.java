package com.sti.facturacion.exception;

import com.sti.facturacion.model.Invoice;

/**
 * Invoice 404 status exception.
 * @author Laurent Geovanny Caceres
 */
public class InvoiceNotFoundException extends ResourceNotFoundException {


    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildInvoiceNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Invoice.class, field, fieldValue);
    }

    /**
     *
     * @param invoiceId
     * @return
     */
    public static ResourceNotFoundException
    buildInvoiceNotFoundExceptionForId(String invoiceId){
        return resourceNotFoundExceptionOf(Invoice.class, "invoiceId", invoiceId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildInvoiceNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Invoice.class, searchParams);
    }
}
