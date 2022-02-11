package com.sti.facturacion.dto.openapi;


import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for CustomerDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseCustomerDto")
public class ResponseCustomerDto extends BaseResponse<CustomerDto> {
}
