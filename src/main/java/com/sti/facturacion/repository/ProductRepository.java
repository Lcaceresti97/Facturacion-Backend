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


}
