package com.sti.facturacion.repository;

import com.sti.facturacion.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Customer entity.
 * @author Laurent Geovanny Caceres
 * @version 1.0.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    /**
     * Find Customer by its name.
     * @param customerName String
     * @return Optional Customer
     */
    Optional<Customer> findByCustomerName(String customerName);


    /**
     * Find Paginated customers by name.
     * @param customerName
     * @param pageable
     * @return
     */
    Page<Customer> findByCustomerNameContaining(String customerName, Pageable pageable);


    /**
     * Find Paginated customers by name.
     * @param customerId
     * @param pageable
     * @return
     */
    Page<Customer> findByCustomerIdContaining(String customerId, Pageable pageable);

}
