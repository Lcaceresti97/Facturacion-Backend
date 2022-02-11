package com.sti.facturacion.controllers;


import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.dto.openapi.PageResponseInvoiceDto;
import com.sti.facturacion.dto.openapi.ResponseCustomerDto;
import com.sti.facturacion.dto.pageable.PageResponse;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import com.sti.facturacion.response.BaseResponse;
import com.sti.facturacion.response.Response;
import com.sti.facturacion.response.error.ErrorResponse;
import com.sti.facturacion.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = "/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private  final InvoiceService invoiceService;



    /**
     * Get Paginated sorted invoices with given criteria.
     *
     * @param entre Date entre
     * @param hasta Date hasta
     * @param page          Page number
     * @param size          page size
     * @param sort          Sort params
     * @return ResponseEntity PageResponse InvoiceDto
     */
    @Operation(summary = "Get a list of invoices between dates paged/ordered", operationId = "getInvoicesByDate")
    @ApiResponse(responseCode = "200", description = "List of invoices retrieved successfully."
            , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponseInvoiceDto.class))})
    @GetMapping(params = {"entre", "hasta","page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<InvoiceDto>> getInvoicesByDate(
            @RequestParam(required = false) Date entre,
            @RequestParam(required = false) Date hasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "id_invoice, desc") String[] sort) {

        Page<InvoiceDto> invoiceDtoPagePage = invoiceService
                .findPaginatedSortedInvoices(entre, hasta, page, size, sort);

        PageResponseDto<InvoiceDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(invoiceDtoPagePage.getSize(), invoiceDtoPagePage.getNumberOfElements(),
                invoiceDtoPagePage.getTotalPages(), invoiceDtoPagePage.getNumber(), invoiceDtoPagePage.getContent());
    }


    /**
     * Handler method for fetching a single Invoice by its ID.
     * @param invoiceId String
     * @return ResponseEntity Invoice
     */
    @Operation(description = "Find a invoice by its ID.", operationId = "findByInvoiceId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  CustomerDto.class))})
            , @ApiResponse(responseCode = "404", description = "Invoice not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/{invoiceId}")
    public ResponseEntity<? extends InvoiceDto> findByInvoiceId(@PathVariable final String invoiceId){
        InvoiceDto invoiceDto = invoiceService.findInvoiceById(invoiceId);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }


    @Operation(summary = "Save given Invoice.", operationId = "saveInvoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",  description = "Invoice saved successfully"
                    ,content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ResponseCustomerDto.class))})
            , @ApiResponse(responseCode = "400", description = "Given Invoice is invalid"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})}
    )
    @PostMapping
    public ResponseEntity<? extends Response<InvoiceDto>> saveInvoice(@RequestBody @Valid InvoiceDto invoiceDto){
        InvoiceDto saveInvoice = invoiceService.saveInvoice(invoiceDto);
        BaseResponse<InvoiceDto> invoiceDtoBaseResponse = new BaseResponse<>();
        return invoiceDtoBaseResponse.buildResponseEntity(HttpStatus.CREATED,"Invoice saved successfully", saveInvoice);
    }
}
