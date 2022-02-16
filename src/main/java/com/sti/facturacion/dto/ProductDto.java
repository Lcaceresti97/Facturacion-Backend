package com.sti.facturacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.model.InvoiceDetails;
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
public class ProductDto {

    @JsonProperty("id_product")
    private String productId;

    @JsonProperty("product_name")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 100)
    private String productName;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Double invoiceDetailPrice;

    @JsonProperty("stock")
    @Positive
    @NotNull
    private Integer productStock;

    @JsonProperty("status")
    private ModelStatus productStatus;

}
