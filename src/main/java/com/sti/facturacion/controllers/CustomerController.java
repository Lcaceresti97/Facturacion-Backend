package com.sti.facturacion.controllers;


import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.dto.openapi.PageResponseCustomerDto;
import com.sti.facturacion.dto.openapi.ResponseCustomerDto;
import com.sti.facturacion.dto.pageable.PageResponse;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import com.sti.facturacion.response.BaseResponse;
import com.sti.facturacion.response.Response;
import com.sti.facturacion.response.error.ErrorResponse;
import com.sti.facturacion.service.CustomerService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    /**
     * Get Paginated sorted CUSTOMER with given criteria.
     *
     * @param customerId String customerId
     * @param page          Page number
     * @param size          page size
     * @param sort          Sort params
     * @return ResponseEntity PageResponse CustomerDto
     */
    @Operation(summary = "Get a list of clients with their invoices paginated/ordered", operationId = "getCustomerById")
    @ApiResponse(responseCode = "200", description = "List of customer retrieved successfully."
            , content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponseCustomerDto.class))})
    @GetMapping(params = {"customerId", "page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<CustomerDto>> getCustomerById(
            @RequestParam(required = false) String customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "customerId, desc") String[] sort) {

        Page<CustomerDto> customerDtoPage = customerService
                .findPaginatedSortedCustomer(customerId, page, size, sort);

        PageResponseDto<CustomerDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(customerDtoPage.getSize(), customerDtoPage.getNumberOfElements(),
                customerDtoPage.getTotalPages(), customerDtoPage.getNumber(), customerDtoPage.getContent());
    }


    /**
     * Handler method for fetching a single Customer by its ID.
     * @param customerId String
     * @return ResponseEntity Customer
     */
    @Operation(description = "Find a customer by its ID.", operationId = "findByCustomerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  CustomerDto.class))})
            , @ApiResponse(responseCode = "404", description = "Customer not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<? extends CustomerDto> findByCustomerId(@PathVariable final String customerId) {
        CustomerDto retrievedCustomer = customerService.findCustomerById(customerId);
        return new ResponseEntity<>(retrievedCustomer, HttpStatus.OK);
    }


    @Operation(summary = "Save given Customer.", operationId = "saveCustomer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",  description = "Customer saved successfully"
                    ,content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ResponseCustomerDto.class))})
            , @ApiResponse(responseCode = "400", description = "Given Customer is invalid"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})}
    )
    @PostMapping
    public ResponseEntity<? extends Response<CustomerDto>> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto savedCustomer = customerService.saveCustomer(customerDto);
        BaseResponse<CustomerDto> customerBaseResponse = new BaseResponse<>();
        return customerBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Customer saved successfully", savedCustomer);
    }

    /**
     * Handler method for deleting a Customer by its ID.
     * @param customerId String
     * @return Response null
     */
    @Operation(description = "Delete a customer by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully."
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  String.class))})
            , @ApiResponse(responseCode = "404", description = "Customer not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<? extends Response<String>> deleteCustomer(@PathVariable final String customerId) {
        customerService.deleteCustomerById(customerId);
        BaseResponse<String> customerResponse = new BaseResponse<>();
        return customerResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Customer with ID: ")
                        .append(customerId)
                        .append(" was deleted.").toString(), customerId);
    }

}
