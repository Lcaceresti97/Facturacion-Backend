package com.sti.facturacion.service.implementation;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.model.mappers.InvoiceDetailsMapper;
import com.sti.facturacion.repository.InvoiceDetailsRepository;
import com.sti.facturacion.service.InvoiceDetailsService;
import com.sti.facturacion.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {


    private final InvoiceDetailsRepository invoiceDetailsRepository;

    private final InvoiceDetailsMapper invoiceDetailsMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public Page<InvoiceDetailsDto> findPaginatedSortedInvoicesDetails(String search, int page, int size, String[] sort) {

        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<InvoiceDetailsDto> invoiceDetailsDto;
        if(search == null) {
            invoiceDetailsDto = invoiceDetailsMapper
                    .invoiceDetailsToDto(invoiceDetailsRepository.findAll(pageable).toList());
        } else {
            invoiceDetailsDto = invoiceDetailsMapper
                    .invoiceDetailsToDto(invoiceDetailsRepository
                            .findInvoiceDetailsByProduct(search, pageable).toList());
        }
        return new PageImpl<>(invoiceDetailsDto);
    }
}
