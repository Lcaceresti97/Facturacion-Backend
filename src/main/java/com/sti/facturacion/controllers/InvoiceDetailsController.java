package com.sti.facturacion.controllers;

import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.dto.openapi.PageResponseInvoiceDetailsDto;
import com.sti.facturacion.dto.openapi.PageResponseInvoiceDto;
import com.sti.facturacion.dto.pageable.PageResponse;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import com.sti.facturacion.service.InvoiceDetailsService;
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
@RequestMapping(path = "/api/v1/invoicesDetails")
@RequiredArgsConstructor
public class InvoiceDetailsController {

    private  final InvoiceDetailsService invoiceDetailsService ;

    /**
     * Get Paginated sorted InvoiceDetails with given criteria.
     *
     * @param search        String search
     * @param page          Page number
     * @param size          page size
     * @param sort          Sort params
     * @return ResponseEntity PageResponse InvoiceDetailsDto
     */
    @Operation(summary = "Get all invoice details where product name is similar paginated/sorted", operationId = "getInvoicesDetailsByProduct")
    @ApiResponse(responseCode = "200", description = "List of invoices details retrieved successfully."
            , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponseInvoiceDetailsDto.class))})
    @GetMapping(params = {"search","page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<InvoiceDetailsDto>> getInvoicesDetailsByProduct(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id_invoice_detail, desc") String[] sort) {

        Page<InvoiceDetailsDto> invoiceDetailsDtoPage = invoiceDetailsService
                .findPaginatedSortedInvoicesDetails(search, page, size, sort);

        PageResponseDto<InvoiceDetailsDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(invoiceDetailsDtoPage.getSize(), invoiceDetailsDtoPage.getNumberOfElements(),
                invoiceDetailsDtoPage.getTotalPages(), invoiceDetailsDtoPage.getNumber(), invoiceDetailsDtoPage.getContent());
    }

}
