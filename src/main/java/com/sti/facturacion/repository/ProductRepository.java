package com.sti.facturacion.repository;

import com.sti.facturacion.model.InvoiceDetails;
import com.sti.facturacion.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Product entity.
 * @author Laurent Geovanny Caceres
 * @version 1.0.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * Find Product by its name.
     * @param productName String
     * @return Optional Product
     */
    Optional<Product> findByProductName(String productName);

    @Query(value = "SELECT * FROM t_invoice_detail i INNER JOIN t_products p ON i.id_product=p.id_product WHERE p.product_name like %:search%" , nativeQuery = true)
    Page<Product> findInvoiceDetailsByProduct(String search, Pageable pageable);

}
