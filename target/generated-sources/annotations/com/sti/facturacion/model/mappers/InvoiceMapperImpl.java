package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.dto.InvoiceDto.InvoiceDtoBuilder;
import com.sti.facturacion.model.Invoice;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-10T15:45:53-0600",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice dtoToInvoice(InvoiceDto dto) {
        if ( dto == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setCustomer( dto.getCustomer() );
        invoice.setInvoiceStatus( dto.getInvoiceStatus() );
        invoice.setInvoiceCode( dto.getInvoiceCode() );

        return invoice;
    }

    @Override
    public InvoiceDto invoiceToDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDtoBuilder invoiceDto = InvoiceDto.builder();

        invoiceDto.invoiceId( invoice.getInvoiceId() );
        invoiceDto.invoiceCode( invoice.getInvoiceCode() );
        invoiceDto.customer( invoice.getCustomer() );
        invoiceDto.invoiceStatus( invoice.getInvoiceStatus() );

        return invoiceDto.build();
    }

    @Override
    public List<Invoice> dtoToInvoice(List<InvoiceDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Invoice> list = new ArrayList<Invoice>( dtos.size() );
        for ( InvoiceDto invoiceDto : dtos ) {
            list.add( dtoToInvoice( invoiceDto ) );
        }

        return list;
    }

    @Override
    public List<InvoiceDto> invoiceToDto(List<Invoice> invoices) {
        if ( invoices == null ) {
            return null;
        }

        List<InvoiceDto> list = new ArrayList<InvoiceDto>( invoices.size() );
        for ( Invoice invoice : invoices ) {
            list.add( invoiceToDto( invoice ) );
        }

        return list;
    }
}
