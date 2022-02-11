package com.sti.facturacion.dto.openapi;

import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseProductDto")
public class PageResponseProductDto extends PageResponseDto<ProductDto> {
}
