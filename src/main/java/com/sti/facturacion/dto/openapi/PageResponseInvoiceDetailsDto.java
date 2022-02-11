package com.sti.facturacion.dto.openapi;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseInvoiceDetailsDto")
public class PageResponseInvoiceDetailsDto extends PageResponseDto<InvoiceDetailsDto> {
}
