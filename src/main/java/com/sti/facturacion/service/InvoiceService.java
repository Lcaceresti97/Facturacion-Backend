package com.sti.facturacion.service;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.exception.InvoiceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface InvoiceService {

    /**
     * Save Invoice entity.
     * @param invoiceDto SubjectDto
     */

    InvoiceDto saveInvoice(InvoiceDto invoiceDto);


    /**
     * Find a Invoice by its ID.
     * @param invoiceId
     * @throws InvoiceNotFoundException
     */
    InvoiceDto findInvoiceById(final String invoiceId) throws InvoiceNotFoundException;


    /**
     * Delete Invoice, in reality it just modifies Invoice Status Code.
     * @param invoiceId
     */
    void deleteInvoiceById(final String invoiceId);

    /**
     * Evaluate if subject exists by subjectName.
     * @param invoiceCode String
     * @return  boolean
     */
    boolean invoiceExists(String invoiceCode);

    /**
     * Return a page of sorted invoices.
     * @param entre Invoice entre to sort by.
     * @param hasta Invoice hasta to sort by.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Student.
     */
    Page<InvoiceDto> findPaginatedSortedInvoices(Date entre, Date hasta, int page, int size, String[] sort);



}
