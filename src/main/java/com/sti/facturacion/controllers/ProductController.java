package com.sti.facturacion.controllers;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.dto.openapi.PageResponseInvoiceDetailsDto;
import com.sti.facturacion.dto.openapi.PageResponseProductDto;
import com.sti.facturacion.dto.pageable.PageResponse;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import com.sti.facturacion.service.InvoiceDetailsService;
import com.sti.facturacion.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private  final ProductService productService;


    /**
     * Get Paginated sorted product with given criteria.
     *
     * @param search        String search
     * @param page          Page number
     * @param size          page size
     * @param sort          Sort params
     * @return ResponseEntity PageResponse ProductDto
     */
    @Operation(summary = "Get a list of paginated/sorted invoices details by product", operationId = "getInvoicesDetailsByProduct")
    @ApiResponse(responseCode = "200", description = "List of invoices details retrieved successfully."
            , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponseProductDto.class))})
    @GetMapping(params = {"search","page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<ProductDto>> getInvoicesDetailsByProduct(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "id_product, desc") String[] sort) {

        Page<ProductDto> productDto = productService
                .findPaginatedSortedProductInvoicesDetails(search, page, size, sort);

        PageResponseDto<ProductDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(productDto.getSize(), productDto.getNumberOfElements(),
                productDto.getTotalPages(), productDto.getNumber(), productDto.getContent());
    }

}
