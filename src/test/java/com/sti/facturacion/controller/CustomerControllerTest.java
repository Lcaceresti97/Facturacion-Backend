package com.sti.facturacion.controller;

import com.sti.facturacion.config.WebMvcConfiguration;
import com.sti.facturacion.controllers.CustomerController;
import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.service.CustomerService;
import com.sti.facturacion.utils.SortingPagingUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = CustomerController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Import(WebMvcConfiguration.class)
public class CustomerControllerTest extends AbstractTestController {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private SortingPagingUtils sortingPagingUtils;

    private static  final String baseUri = "/api/v1/customers";

    private CustomerDto customerDto;

    private Customer customer;

    private static final String CUSTOMER_ID = UUID.randomUUID().toString();

    private static final String CUSTOMER_NAME = "Laurent Caceres";


    final int PAGE_NUMBER = 0;

    final int PAGE_SIZE = 3;

    final String[] SORT = new String[]{"customerId, desc"};


    @Before
    public void init(){
        customerDto = CustomerDto
                .builder()
                .customerId(CUSTOMER_ID)
                .customerName(CUSTOMER_NAME)
                .customerAddress("Col. Quezada")
                .customerPhone(96547894)
                .customerInvoice(new ArrayList<>())
                .build();

        customer = Customer
                .builder()
                .customerId(CUSTOMER_ID)
                .customerName(CUSTOMER_NAME)
                .customerAddress("Col. Quezada")
                .customerPhone(96587487)
                .customerInvoice(new ArrayList<>())
                .build();
    }

    @Test
    @Ignore
    public void shouldGetPaginatedSortedCustomers() throws Exception{
        Page<CustomerDto> customerDtoPage = new PageImpl<>(List.of(customerDto));

        when(customerService.findPaginatedSortedCustomer(CUSTOMER_ID,PAGE_NUMBER, PAGE_SIZE, SORT)).thenReturn(customerDtoPage);

        doRequestGetPaginatedSortedCustomers()
                .andExpect(status().isOk());
    }

    private ResultActions doRequestGetPaginatedSortedCustomers() throws Exception{
        return getMockMvc()
                .perform(get(baseUri )
                        .param("customerId", CUSTOMER_ID)
                        .param("page", String.valueOf(PAGE_NUMBER))
                        .param("size", String.valueOf(PAGE_SIZE))
                        .param("sort", SORT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldSaveCustomer() throws Exception{
        when(customerService.saveCustomer(customerDto)).thenReturn(customerDto);

        doRequestSaveCustomer(customerDto).andExpect(status().isCreated());
    }

    private ResultActions doRequestSaveCustomer(final CustomerDto customerDto) throws Exception {
        return getMockMvc()
                .perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getObjectMapper().writeValueAsString(customerDto)));
    }

    @Test
    public void shouldDeleteById() throws Exception{
        doNothing().when(customerService)
                .deleteCustomerById(CUSTOMER_ID);

        doRequestDeleteCustomerById()
                .andExpect(status().isOk());
    }

    private ResultActions doRequestDeleteCustomerById() throws  Exception{
        return getMockMvc()
                .perform(delete(baseUri + "/{customerId}", CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));
    }

}
