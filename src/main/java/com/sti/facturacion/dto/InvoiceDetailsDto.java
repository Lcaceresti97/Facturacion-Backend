package com.sti.facturacion.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.model.InvoiceDetails;
import com.sti.facturacion.model.Product;
import com.sti.facturacion.model.status.ModelStatus;
import lombok.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer DTO class to encapsulate implementation of entity.
 * @author Laurent Geovanny Caceres
 * @version 1.0
 */
@JsonSerialize
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailsDto {


    @JsonProperty("id_invoice_detail")
    private String invoiceDetailId;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Integer invoiceDetailAmount;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailTotalParcial;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailIsv;

    @JsonProperty(required = true)
    @NotNull
    private Double invoiceDetailDiscount;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailTotal;

    @JsonProperty("status")
    private ModelStatus invoiceDetailStatus;

    @JsonProperty("product")
    private Product product;

}
