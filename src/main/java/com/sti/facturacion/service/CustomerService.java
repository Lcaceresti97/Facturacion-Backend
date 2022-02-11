package com.sti.facturacion.service;

import com.sti.facturacion.dto.CustomerDto;
import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.exception.CustomerNotFoundException;
import com.sti.facturacion.model.Customer;
import com.sti.facturacion.model.Invoice;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto customerDto);

    /**
     * Find a customer by its ID.
     * @param customerId String
     * @return Customer CustomerDto
     * @throws CustomerNotFoundException when no Customer is found by ID
     */
    CustomerDto findCustomerById(final String customerId) throws CustomerNotFoundException;

    CustomerDto findCustomerByName(final String customerName)throws CustomerNotFoundException;

    void deleteCustomerById(final String customerId);

    Page<CustomerDto> findPaginatedSortedCustomer(String customerId, int page, int size, String[] sort);

    void addInvoiceToCustomer(String customerId, InvoiceDto invoiceDto);


}
