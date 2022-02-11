package com.sti.facturacion.dto.openapi;


import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseCustomerDto")
public class PageResponseCustomerDto extends PageResponseDto<CustomerDto> {
}
