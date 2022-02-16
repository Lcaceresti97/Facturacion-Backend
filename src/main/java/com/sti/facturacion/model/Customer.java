package com.sti.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sti.facturacion.model.status.ModelStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "t_customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {

    @Id
    @Column(name = "id_customer", nullable = false, unique = true , length = 64)
    private String customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "address", nullable = false)
    private String customerAddress;

    @Column(name= "phone", nullable = false)
    private Integer customerPhone;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus customerStatus;

    @OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Invoice> customerInvoice = new ArrayList<>();


    public List<Invoice> getCustomerInvoice() {
        return customerInvoice;
    }

    public void setCustomerInvoice(List<Invoice> customerInvoice) {
        this.customerInvoice = customerInvoice;
    }

    /**
     * Adds fields which are not populated by Customer DTO.
     * @return
     */
    public static Customer buildFromDtoCustomer(Customer customer){
        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setCustomerStatus(ModelStatus.ACTIVE);
        return customer;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Customer customer = (Customer) o;
        return this.customerId == customer.customerId
                && (this.customerName.equals(customer.customerName))
                && (this.customerAddress.equals(customer.customerPhone));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.customerName == null ? 0 : this.customerName.hashCode());
        hash = 31 * hash + (this.customerId == null ? 0 : this.customerId.hashCode());
        hash = 31 * hash + (this.customerAddress == null ? 0 : this.customerAddress.hashCode());
        return hash;
    }


    private void setCustomerId(final String customerId) { this.customerId = customerId;}

    public void setCustomerStatus(ModelStatus modelStatus) { this.customerStatus = modelStatus;}

    public void addInvoice(Invoice invoice){
        this.customerInvoice.add(invoice);
        invoice.setInvoiceCustomer(this);
    }

    public void removeInvoice(Invoice invoice){
        this.customerInvoice.remove(invoice);
        invoice.setInvoiceCustomer(null);
    }

}
