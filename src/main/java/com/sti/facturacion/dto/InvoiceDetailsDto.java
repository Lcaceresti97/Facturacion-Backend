package com.sti.facturacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.facturacion.model.Invoice;
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
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailsDto {

    @JsonProperty("id_invoice_detail")
    private Integer invoiceDetailId;

    @JsonProperty("invoice")
    private Invoice invoice;

    @JsonProperty("product")
    private Product product;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Integer invoiceDetailAmount;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailPrice;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailTotalParcial;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailIsv;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailDiscount;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailTotal;

    @JsonProperty("status")
    private ModelStatus invoiceDetailStatus;
}
