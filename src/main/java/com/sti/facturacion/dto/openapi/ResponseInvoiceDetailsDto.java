package com.sti.facturacion.dto.openapi;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for InvoiceDetailsDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseInvoiceDetailsDto")
public class ResponseInvoiceDetailsDto extends BaseResponse<InvoiceDetailsDto> {
}
