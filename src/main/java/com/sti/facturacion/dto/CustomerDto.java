package com.sti.facturacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.facturacion.model.Invoice;
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
public class CustomerDto {

    @JsonProperty("id_customer")
    private String customerId;

    @JsonProperty("customer_name")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 100)
    private String customerName;

    @JsonProperty("address")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 100)
    private String customerAddress;

    @JsonProperty(required = true)
    @Positive
    @NotNull
    private Integer customerPhone;

    @JsonProperty("status")
    private ModelStatus customerStatus;

    @JsonProperty("customerInvoice")
    @Builder.Default
    private List<Invoice> customerInvoice = new ArrayList<>();

}
