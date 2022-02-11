package com.sti.facturacion.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageKey     {

    //Not found.
    CUSTOMER_NOT_FOUND("customer-not-found"),
    INVOICE_NOT_FOUND("invoice-not-found"),
    INVOICE_DETAILS_NOT_FOUND("invoice-details-not-found"),
    PRODUCT_NOT_FOUND("product-not-found"),

    //Customer Service
    CUSTOMER_INVALID_INVOICE("customer-invoice-invalid");

    public final String key;

    public String getKey(){
        return this.key;
    }
}
