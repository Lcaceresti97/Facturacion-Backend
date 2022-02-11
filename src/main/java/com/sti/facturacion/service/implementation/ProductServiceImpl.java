package com.sti.facturacion.service.implementation;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.model.mappers.InvoiceDetailsMapper;
import com.sti.facturacion.model.mappers.ProductMapper;
import com.sti.facturacion.repository.InvoiceDetailsRepository;
import com.sti.facturacion.repository.ProductRepository;
import com.sti.facturacion.service.ProductService;
import com.sti.facturacion.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public Page<ProductDto> findPaginatedSortedProductInvoicesDetails(String search, int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<ProductDto> productDto;
        if(search == null) {
            productDto = productMapper
                    .productTodto(productRepository.findAll(pageable).toList());
        } else {
            productDto = productMapper
                    .productTodto(productRepository
                            .findInvoiceDetailsByProduct(search, pageable).toList());
        }
        return new PageImpl<>(productDto);
    }
}
