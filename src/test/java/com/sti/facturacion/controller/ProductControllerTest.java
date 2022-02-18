package com.sti.facturacion.controller;

import com.sti.facturacion.config.WebMvcConfiguration;
import com.sti.facturacion.controllers.ProductController;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.model.Product;
import com.sti.facturacion.repository.ProductRepository;
import com.sti.facturacion.service.ProductService;
import com.sti.facturacion.utils.SortingPagingUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ProductController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Import(WebMvcConfiguration.class)
public class ProductControllerTest extends AbstractTestController{

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private SortingPagingUtils sortingPagingUtils;

    @MockBean
    private ProductService productService;

    private static final String baseUri = "/api/v1/product";

    private ProductDto productDto;

    private Product product;

    private final static String PRODUCT_ID = UUID.randomUUID().toString();

    private final static String PRODUCT_NAME = UUID.randomUUID().toString();

    @Before
    public void init(){
        productDto = ProductDto.builder()
                .productId(PRODUCT_ID)
                .productName(PRODUCT_NAME)
                .productPrice(500.00)
                .productStock(30)
                .build();


    }

    @Test
    public void shouldGetProductById() throws Exception{
        when(productService.findProductById(PRODUCT_ID)).thenReturn(productDto);

        doRequestGetProductById().andExpect(status().isOk());
    }

    private ResultActions doRequestGetProductById() throws Exception {
        return getMockMvc()
                .perform(get(baseUri + "/{productId}", PRODUCT_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldSaveProduct() throws Exception {
        when(productService.saveProduct(productDto)).thenReturn(productDto);

        doRequestSaveProduct(productDto).andExpect(status().isCreated());
    }


    private ResultActions doRequestSaveProduct(final ProductDto productDto) throws Exception {
        return getMockMvc()
                .perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getObjectMapper()
                                .writeValueAsString(productDto)));
    }


    @Test
    public void shouldDeleteProductById() throws Exception {
        doNothing()
                .when(productService)
                .deleteProductById(PRODUCT_ID);

        doRequestDeleteProductById()
                .andExpect(status().isOk());
    }


    private ResultActions doRequestDeleteProductById() throws Exception {
        return getMockMvc()
                .perform(delete(baseUri + "/{productId}", PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }

}
