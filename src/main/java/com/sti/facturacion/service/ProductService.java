package com.sti.facturacion.service;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface ProductService {

    /**
     * Return a page of sorted invoices.
     * @param search Invoice hasta to sort by.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Student.
     */
    Page<ProductDto> findPaginatedSortedProductInvoicesDetails(String search, int page, int size, String[] sort);


}