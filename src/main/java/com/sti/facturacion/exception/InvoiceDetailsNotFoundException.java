package com.sti.facturacion.exception;

import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.model.InvoiceDetails;

/**
 * Invoice Details 404 status exception.
 * @author Laurent Geovanny Caceres
 */
public class InvoiceDetailsNotFoundException extends ResourceNotFoundException{

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildInvoiceDetailsNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(InvoiceDetails.class, field, fieldValue);
    }

    /**
     *
     * @param invoiceDetailId
     * @return
     */
    public static ResourceNotFoundException
    buildInvoiceNotFoundExceptionForId(String invoiceDetailId){
        return resourceNotFoundExceptionOf(InvoiceDetails.class, "invoiceDetailId", invoiceDetailId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildInvoiceNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(InvoiceDetails.class, searchParams);
    }
}
