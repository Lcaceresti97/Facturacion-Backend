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
    private Integer productId;

    @JsonProperty("product_name")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 100)
    private String productName;

    @JsonProperty("stock")
    @Positive
    @NotBlank
    private Integer productStock;

    @JsonProperty("status")
    private ModelStatus productStatus;

   // @JsonProperty("productInvoiceDetails")
    //@Builder.Default
    //private List<InvoiceDetails> productInvoiceDetails = new ArrayList<>();

}
