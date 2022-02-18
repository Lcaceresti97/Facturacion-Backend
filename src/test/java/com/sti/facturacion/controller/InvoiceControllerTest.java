package com.sti.facturacion.controller;

import com.sti.facturacion.config.WebMvcConfiguration;
import com.sti.facturacion.controllers.InvoiceController;
import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.service.InvoiceService;
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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = InvoiceController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Import(WebMvcConfiguration.class)
public class InvoiceControllerTest extends AbstractTestController{


    @MockBean
    private InvoiceService invoiceService;

    @Autowired
    private SortingPagingUtils sortingPagingUtils;

    private static  final String baseUri = "/api/v1/invoices";

    private Invoice invoice;

    private InvoiceDto invoiceDto;

    private CustomerDto customerDto;

    private Customer customer;

    private static final String INVOICE_ID = UUID.randomUUID().toString();

    private static final String CUSTOMER_ID = UUID.randomUUID().toString();

    final int PAGE_NUMBER = 0;

    final int PAGE_SIZE = 3;

    final Date ENTRE = new Date();

    final Date HASTA = new Date();

    private static final String[] sort = new String[]{"invoiceId,desc"};

    @Before
    public void init(){

        invoiceDto = InvoiceDto
                .builder()
                .invoiceId(INVOICE_ID)
                .invoiceCode("AEKS97")
                .build();
    }

    @Test
    @Ignore
    public void shouldGetPaginatedSortedInvoices() throws Exception {
        Page<InvoiceDto> invoiceDtoPage = new PageImpl<>(List.of(invoiceDto));
        when(invoiceService
                .findPaginatedSortedInvoices(ENTRE, HASTA, PAGE_NUMBER, PAGE_SIZE, sort))
                .thenReturn(invoiceDtoPage);

        doRequestGetPaginatedSortedInvoicesByDate()
                .andExpect(status().isOk());

    }

    private ResultActions doRequestGetPaginatedSortedInvoicesByDate() throws Exception{
        return getMockMvc()
                .perform(get(baseUri)
                        .param("invoiceId", INVOICE_ID)
                        .param("page", String.valueOf(PAGE_NUMBER))
                        .param("size", String.valueOf(PAGE_SIZE))
                        .param("sort", sort)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldSaveInvoice() throws Exception{
        when(invoiceService.saveInvoice(invoiceDto)).thenReturn(invoiceDto);

        doRequestSaveInvoice(invoiceDto).andExpect(status().isCreated());
    }

    private ResultActions doRequestSaveInvoice(final InvoiceDto invoiceDto) throws Exception {
        return getMockMvc()
                .perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getObjectMapper().writeValueAsString(invoiceDto)));
    }

    @Test
    public void shouldGetInvoiceById() throws Exception{
        when(invoiceService.findInvoiceById(INVOICE_ID)).thenReturn(invoiceDto);

        doRequestGetInvoiceById().andExpect(status().isOk());
    }

    private ResultActions doRequestGetInvoiceById() throws Exception {
        return getMockMvc()
                .perform(get(baseUri + "/{invoiceId}", INVOICE_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));
    }


}
