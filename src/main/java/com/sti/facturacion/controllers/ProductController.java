package com.sti.facturacion.controllers;

import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.dto.openapi.PageResponseInvoiceDetailsDto;
import com.sti.facturacion.dto.openapi.PageResponseProductDto;
import com.sti.facturacion.dto.openapi.ResponseCustomerDto;
import com.sti.facturacion.dto.openapi.ResponseProductDto;
import com.sti.facturacion.dto.pageable.PageResponse;
import com.sti.facturacion.dto.pageable.PageResponseDto;
import com.sti.facturacion.response.BaseResponse;
import com.sti.facturacion.response.Response;
import com.sti.facturacion.response.error.ErrorResponse;
import com.sti.facturacion.service.InvoiceDetailsService;
import com.sti.facturacion.service.ProductService;
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

@RestController
@RequestMapping(path = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private  final ProductService productService;


    /**
     * Handler method for fetching a single Product by its ID.
     * @param productId String
     * @return ResponseEntity Product
     */
    @Operation(description = "Find a product by its ID.", operationId = "findByProductId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  ProductDto.class))})
            , @ApiResponse(responseCode = "404", description = "Product not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/{productId}")
    public ResponseEntity<? extends ProductDto> findByProductId(@PathVariable final String productId) {
        ProductDto retrievedProduct = productService.findProductById(productId);
        return new ResponseEntity<>(retrievedProduct, HttpStatus.OK);
    }


    @Operation(summary = "Save given Product.", operationId = "saveProduct")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",  description = "Product saved successfully"
                    ,content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ResponseProductDto.class))})
            , @ApiResponse(responseCode = "400", description = "Given Product is invalid"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})}
    )
    @PostMapping
    public ResponseEntity<? extends Response<ProductDto>> saveProduct(@RequestBody @Valid ProductDto productDto) {
        ProductDto savedProduct = productService.saveProduct(productDto);
        BaseResponse<ProductDto> productDtoBaseResponse = new BaseResponse<>();
        return productDtoBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Product saved successfully", savedProduct);
    }


    /**
     * Handler method for deleting a Product by its ID.
     * @param productId String
     * @return Response null
     */
    @Operation(description = "Delete a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully."
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  String.class))})
            , @ApiResponse(responseCode = "404", description = "Product not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<? extends Response<String>> deleteProduct(@PathVariable final String productId) {
        productService.deleteProductById(productId);
        BaseResponse<String> productResponse = new BaseResponse<>();
        return productResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Product with ID: ")
                        .append(productId)
                        .append(" was deleted.").toString(), productId);
    }

}
