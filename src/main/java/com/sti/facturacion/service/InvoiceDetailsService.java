package com.sti.facturacion.service;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import org.springframework.data.domain.Page;


public interface InvoiceDetailsService {

    InvoiceDetailsDto saveInvoiceDetails(InvoiceDetailsDto invoiceDetailsDto);

    /**
     * Return a page of sorted invoices.
     * @param search Invoice hasta to sort by.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Student.
     */
    Page<InvoiceDetailsDto> findPaginatedSortedInvoicesDetails(String search, int page, int size, String[] sort);


}
