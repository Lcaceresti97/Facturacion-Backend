package com.sti.facturacion.service.implementation;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.exception.InvoiceNotFoundException;
import com.sti.facturacion.model.Invoice;
import com.sti.facturacion.model.mappers.InvoiceMapper;
import com.sti.facturacion.model.status.ModelStatus;
import com.sti.facturacion.repository.InvoiceRepository;
import com.sti.facturacion.service.InvoiceService;
import com.sti.facturacion.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceimpl implements InvoiceService {


    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public InvoiceDto saveInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = Invoice
                .buildFromDtoInvoice(invoiceMapper.dtoToInvoice(invoiceDto));
        invoiceRepository.save(invoice);
        return invoiceMapper.invoiceToDto(invoice);
    }

    @Override
    public InvoiceDto findInvoiceById(final String invoiceId)  {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> InvoiceNotFoundException
                        .buildInvoiceNotFoundExceptionForId(invoiceId));
        return invoiceMapper
                .invoiceToDto(
                        isActiveInvoice(invoice, "invoiceId", invoiceId));
    }


    @Override
    public void deleteInvoiceById(final String invoiceId) {
        Invoice invoice = invoiceMapper.dtoToInvoice(findInvoiceById(invoiceId));
        invoice.setInvoiceStatus(ModelStatus.INACTIVE);
        invoiceRepository.save(invoice);
    }

    @Override
    public boolean invoiceExists(String invoiceCode) {
        return invoiceRepository.existsByInvoiceCodeIgnoreCase(invoiceCode);
    }

    @Override
    public Page<InvoiceDto> findPaginatedSortedInvoices
            (final Date entre, final Date hasta, final int page, final int size, final String[] sort) {

        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<InvoiceDto> invoiceDto;
        if(entre == null || hasta ==null) {
            invoiceDto = invoiceMapper
                    .invoiceToDto(invoiceRepository.findAll(pageable).toList());
        } else {
            invoiceDto = invoiceMapper
                    .invoiceToDto(invoiceRepository
                            .findInvoiceEntreFechas(entre, hasta, pageable).toList());
        }
        return new PageImpl<>(invoiceDto);
    }

    /**
     * Return invoice if status code is ACTIVE.
     * @param invoice Invoice
     * @param queryField String
     * @param fieldValue String
     * @return Invoice
     * @throws InvoiceNotFoundException ex
     */
    private Invoice isActiveInvoice(Invoice invoice, String queryField, String fieldValue){
        if(invoice.getInvoiceStatus().getStatusCode() == 0){
            return invoice;
        }
        throw InvoiceNotFoundException
                .buildInvoiceNotFoundExceptionForField(queryField, fieldValue);
    }

}
