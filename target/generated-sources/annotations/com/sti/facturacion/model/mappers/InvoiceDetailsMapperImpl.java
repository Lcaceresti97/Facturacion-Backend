package com.sti.facturacion.model.mappers;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.InvoiceDetailsDto.InvoiceDetailsDtoBuilder;
import com.sti.facturacion.model.InvoiceDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-15T21:41:37-0600",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class InvoiceDetailsMapperImpl implements InvoiceDetailsMapper {

    @Override
    public InvoiceDetails dtoToInvoiceDetails(InvoiceDetailsDto dto) {
        if ( dto == null ) {
            return null;
        }

        InvoiceDetails invoiceDetails = new InvoiceDetails();

        invoiceDetails.setInvoice( dto.getInvoice() );
        invoiceDetails.setProduct( dto.getProduct() );
        invoiceDetails.setInvoiceDetailStatus( dto.getInvoiceDetailStatus() );
        invoiceDetails.setInvoiceDetailId( dto.getInvoiceDetailId() );
        invoiceDetails.setInvoiceDetailAmount( dto.getInvoiceDetailAmount() );
        invoiceDetails.setInvoiceDetailPrice( dto.getInvoiceDetailPrice() );
        invoiceDetails.setInvoiceDetailTotalParcial( dto.getInvoiceDetailTotalParcial() );
        invoiceDetails.setInvoiceDetailIsv( dto.getInvoiceDetailIsv() );
        invoiceDetails.setInvoiceDetailDiscount( dto.getInvoiceDetailDiscount() );
        invoiceDetails.setInvoiceDetailTotal( dto.getInvoiceDetailTotal() );

        return invoiceDetails;
    }

    @Override
    public InvoiceDetailsDto invoiceDetailsToDto(InvoiceDetails invoiceDetails) {
        if ( invoiceDetails == null ) {
            return null;
        }

        InvoiceDetailsDtoBuilder invoiceDetailsDto = InvoiceDetailsDto.builder();

        invoiceDetailsDto.invoiceDetailId( invoiceDetails.getInvoiceDetailId() );
        invoiceDetailsDto.invoice( invoiceDetails.getInvoice() );
        invoiceDetailsDto.product( invoiceDetails.getProduct() );
        invoiceDetailsDto.invoiceDetailAmount( invoiceDetails.getInvoiceDetailAmount() );
        invoiceDetailsDto.invoiceDetailPrice( invoiceDetails.getInvoiceDetailPrice() );
        invoiceDetailsDto.invoiceDetailTotalParcial( invoiceDetails.getInvoiceDetailTotalParcial() );
        invoiceDetailsDto.invoiceDetailIsv( invoiceDetails.getInvoiceDetailIsv() );
        invoiceDetailsDto.invoiceDetailDiscount( invoiceDetails.getInvoiceDetailDiscount() );
        invoiceDetailsDto.invoiceDetailTotal( invoiceDetails.getInvoiceDetailTotal() );
        invoiceDetailsDto.invoiceDetailStatus( invoiceDetails.getInvoiceDetailStatus() );

        return invoiceDetailsDto.build();
    }

    @Override
    public List<InvoiceDetails> dtoToInvoiceDetails(List<InvoiceDetailsDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<InvoiceDetails> list = new ArrayList<InvoiceDetails>( dtos.size() );
        for ( InvoiceDetailsDto invoiceDetailsDto : dtos ) {
            list.add( dtoToInvoiceDetails( invoiceDetailsDto ) );
        }

        return list;
    }

    @Override
    public List<InvoiceDetailsDto> invoiceDetailsToDto(List<InvoiceDetails> invoiceDetails) {
        if ( invoiceDetails == null ) {
            return null;
        }

        List<InvoiceDetailsDto> list = new ArrayList<InvoiceDetailsDto>( invoiceDetails.size() );
        for ( InvoiceDetails invoiceDetails1 : invoiceDetails ) {
            list.add( invoiceDetailsToDto( invoiceDetails1 ) );
        }

        return list;
    }
}
