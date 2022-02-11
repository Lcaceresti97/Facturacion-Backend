package com.sti.facturacion.dto.openapi;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for ProductDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseInvoiceDto")
public class ResponseInvoiceDto extends BaseResponse<InvoiceDto> {
}
