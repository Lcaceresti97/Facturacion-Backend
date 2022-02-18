package com.sti.facturacion.controller;


import com.sti.facturacion.config.WebMvcConfiguration;
import com.sti.facturacion.controllers.InvoiceController;
import com.sti.facturacion.controllers.InvoiceDetailsController;
import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDetailsDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.model.InvoiceDetails;
import com.sti.facturacion.service.InvoiceDetailsService;
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

@ContextConfiguration(classes = InvoiceDetailsController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Import(WebMvcConfiguration.class)
public class InvoiceDetailsControllerTest extends AbstractTestController {

    @MockBean
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private SortingPagingUtils sortingPagingUtils;

    private static  final String baseUri = "/api/v1/invoicesDetails";

    private InvoiceDetails invoiceDetails;

    private InvoiceDetailsDto invoiceDetailsDto;

    private static final String SEARCH = "Ram";

    private static final String INVOICE_DETAILS_ID = UUID.randomUUID().toString();

    final int PAGE_NUMBER = 0;

    final int PAGE_SIZE = 3;

    private static final String[] sort = new String[]{"id_invoice_detail,desc"};

    @Before
    public void init(){
        invoiceDetailsDto = InvoiceDetailsDto
                .builder()
                .invoiceDetailId(INVOICE_DETAILS_ID)
                .invoiceDetailAmount(2)
                .invoiceDetailTotalParcial(150.00)
                .invoiceDetailDiscount(0.00)
                .invoiceDetailIsv(0.15)
                .invoiceDetailTotal(165.00)
                .build();
    }

    @Test
    public void shouldGetPaginatedSortedInvoicesDetails() throws Exception {
        Page<InvoiceDetailsDto> invoiceDetailsDtoPage = new PageImpl<>(List.of(invoiceDetailsDto));
        when(invoiceDetailsService
                .findPaginatedSortedInvoicesDetails(SEARCH, PAGE_NUMBER, PAGE_SIZE, sort))
                .thenReturn(invoiceDetailsDtoPage);

        doRequestGetPaginatedSortedInvoiceDetails()
                .andExpect(status().isOk());

    }


    private ResultActions doRequestGetPaginatedSortedInvoiceDetails() throws Exception{
        return getMockMvc()
                .perform(get(baseUri)
                        .param("search", SEARCH)
                        .param("page", String.valueOf(PAGE_NUMBER))
                        .param("size", String.valueOf(PAGE_SIZE))
                        .param("sort", "id_invoice_detail,desc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldSaveInvoiceDetails() throws Exception{
        when(invoiceDetailsService.saveInvoiceDetails(invoiceDetailsDto)).thenReturn(invoiceDetailsDto);

        doRequestSaveInvoiceDetails(invoiceDetailsDto).andExpect(status().isCreated());
    }

    private ResultActions doRequestSaveInvoiceDetails(final InvoiceDetailsDto invoiceDetailsDto) throws Exception {
        return getMockMvc()
                .perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getObjectMapper().writeValueAsString(invoiceDetailsDto)));
    }

}
