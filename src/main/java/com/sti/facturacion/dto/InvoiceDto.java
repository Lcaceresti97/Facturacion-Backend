package com.sti.facturacion.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.InvoiceDetails;
import com.sti.facturacion.model.status.ModelStatus;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Customer DTO class to encapsulate implementation of entity.
 * @author Laurent Geovanny Caceres
 * @version 1.0
 */
@JsonSerialize
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    @JsonProperty("id_invoice")
    private String invoiceId;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 32)
    private String invoiceCode;

    @JsonProperty("regist_date")
    @Temporal(TemporalType.DATE)
    private Date registDateInvoice;

    @JsonIgnore
    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("status")
    private ModelStatus invoiceStatus;

    @JsonProperty("invoiceDetailsList")
    @Builder.Default
    private List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();

    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
