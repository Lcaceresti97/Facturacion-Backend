package com.sti.facturacion.repository;

import com.sti.facturacion.dto.InvoiceDto;
import com.sti.facturacion.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Invoice entity.
 * @author Laurent Geovanny Caceres
 * @version 1.0.0
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    /**
     * Validate if invoice exists.
     * @param invoiceCode
     * @return
     */
    boolean existsByInvoiceCodeIgnoreCase(String invoiceCode);

    @Query(value = "SELECT * FROM t_invoice WHERE regist_date >= :entre AND regist_date <= :hasta" , nativeQuery
            = true)
    Page<Invoice> findInvoiceEntreFechas(Date entre, Date hasta, Pageable pageable);


}
