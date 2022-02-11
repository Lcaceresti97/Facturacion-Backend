package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.model.Invoice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    Invoice dtoToInvoice(InvoiceDto dto);

    InvoiceDto invoiceToDto(Invoice invoice);

    List<Invoice> dtoToInvoice(List<InvoiceDto> dtos);

    List<InvoiceDto> invoiceToDto(List<Invoice> invoices);

}
