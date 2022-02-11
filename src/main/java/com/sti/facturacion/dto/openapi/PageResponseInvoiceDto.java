package com.sti.facturacion.dto.openapi;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseInvoiceDto")
public class PageResponseInvoiceDto extends PageResponseDto<InvoiceDto> {
}
