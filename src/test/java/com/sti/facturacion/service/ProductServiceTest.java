package com.sti.facturacion.service;

import com.sti.facturacion.config.WebMvcConfiguration;
import com.sti.facturacion.dto.ProductDto;
import com.sti.facturacion.exception.ResourceNotFoundException;
import com.sti.facturacion.model.Product;
import com.sti.facturacion.model.mappers.ProductMapper;
import com.sti.facturacion.model.status.ModelStatus;
import com.sti.facturacion.repository.ProductRepository;
import com.sti.facturacion.service.implementation.ProductServiceImpl;
import com.sti.facturacion.utils.MessageKey;
import com.sti.facturacion.utils.Messages;
import com.sti.facturacion.utils.SortingPagingUtils;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import static org.assertj.core.api.Assertions.fail;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@Import({Messages.class, WebMvcConfiguration.class})
public class ProductServiceTest {

    @Autowired
    private Messages messages;

    @Autowired
    private SortingPagingUtils sortingPagingUtils;

    private Product product;

    private ProductDto productDto;

    private ProductMapper productMapper;

    private ProductService productService;

    private ProductRepository productRepository;

    private static final String PRODUCT_ID = UUID.randomUUID().toString();

    private static final String PRODUCT_NAME = "Procesador AMD";

    private static final Double PRODUCT_PRICE = 8550.00;

    private static final Integer PRODUCT_STOCK = 15;

    private String PRODUCT_NOT_FOUND;

    @Before
    public void init(){

        productRepository = Mockito.mock(ProductRepository.class);
        productMapper = Mappers.getMapper(ProductMapper.class);
        productService = new ProductServiceImpl(productRepository, productMapper, sortingPagingUtils);

        PRODUCT_NOT_FOUND = messages.getMessage(MessageKey.PRODUCT_NOT_FOUND.getKey());

        product = Product
                .builder()
                .productId(PRODUCT_ID)
                .productName(PRODUCT_NAME)
                .productPrice(PRODUCT_PRICE)
                .productStock(PRODUCT_STOCK)
                .productStatus(ModelStatus.ACTIVE)
                .build();


        productDto = ProductDto
                .builder()
                .productId(PRODUCT_ID)
                .productName(PRODUCT_NAME)
                .productPrice(PRODUCT_PRICE)
                .productStock(PRODUCT_STOCK)
                .productStatus(ModelStatus.ACTIVE)
                .build();
    }

    @Test
    public void returnProductById(){

        doReturn(Optional.of(product)).when(productRepository).findById(PRODUCT_ID);

        ProductDto productDto = productService.findProductById(PRODUCT_ID);

        assertThat(productDto).isNotNull();
        assertThat(productDto.getProductId()).isEqualTo(product.getProductId());
        assertThat(productDto.getProductName()).isEqualTo(product.getProductName());
        //assertThat(productDto.getInvoiceDetailPrice()).isEqualTo(product.getProductPrice());
        assertThat(productDto.getProductStock()).isEqualTo(product.getProductStock());

    }

    @Test
    public void whenNotFoundById_throwException(){
        Map<String, String> params = Map.of("productId", PRODUCT_ID);

        final String EX_MESSAGE = new StringBuilder(PRODUCT_NOT_FOUND)
                .append(" ")
                .append(params).toString();


        doReturn(Optional.empty())
                .when(productRepository)
                .findById(PRODUCT_ID);

        try {
            ProductDto productDto = productService.findProductById(PRODUCT_ID);
            fail("Exception must be thrown.");
        }
        catch (Exception e) {
            assertThat(e).isInstanceOf(ResourceNotFoundException.class);
            //assertThat(e.getMessage()).isEqualTo(EX_MESSAGE);
        }
    }

}
