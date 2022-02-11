package com.sti.facturacion.model.mappers;


import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.model.InvoiceDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceDetailsMapper {

    InvoiceDetails dtoToInvoiceDetails(InvoiceDetailsDto dto);

    InvoiceDetailsDto invoiceDetailsToDto(InvoiceDetails invoiceDetails);

    List<InvoiceDetails> dtoToInvoiceDetails(List<InvoiceDetailsDto> dtos);

    List<InvoiceDetailsDto> invoiceDetailsToDto(List<InvoiceDetails> invoiceDetails);
}
