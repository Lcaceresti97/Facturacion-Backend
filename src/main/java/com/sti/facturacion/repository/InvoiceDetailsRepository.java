package com.sti.facturacion.repository;

import com.sti.facturacion.model.InvoiceDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Repository for InvoiceDetails entity.
 * @author Laurent Geovanny Caceres
 * @version 1.0.0
 */
@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, String> {


    @Query(value = "SELECT * FROM t_invoice_detail i INNER JOIN t_products p ON i.id_product=p.id_product WHERE p.product_name like %:search%" , nativeQuery = true)
    Page<InvoiceDetails> findInvoiceDetailsByProduct(String search, Pageable pageable);


}
