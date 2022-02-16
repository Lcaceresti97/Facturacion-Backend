package com.sti.facturacion.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sti.facturacion.model.status.ModelStatus;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "t_invoice")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    @Id
    @Column(name = "id_invoice", nullable = false, unique = true, length = 64)
    private String invoiceId;

    @Column(name = "invoice_code", nullable = false)
    private String invoiceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToMany(mappedBy="invoice", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();

    @Column(name = "regist_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registDateInvoice;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus invoiceStatus;

    @JsonBackReference
    public List<InvoiceDetails> getInvoiceDetailsList() {
        return invoiceDetailsList;
    }

    public void setInvoiceDetailsList(List<InvoiceDetails> invoiceDetailsList) {
        this.invoiceDetailsList = invoiceDetailsList;
    }

    @JsonIgnore
    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }

    @JsonIgnore
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Adds fields which are not populated by Invoice DTO.
     * @return
     */
    public static Invoice buildFromDtoInvoice(Invoice invoice){
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setInvoiceStatus(ModelStatus.ACTIVE);
        return invoice;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return this.invoiceId == invoice.invoiceId
                && (this.invoiceCode.equals(invoice.invoiceCode));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.invoiceCode == null ? 0 : this.invoiceCode.hashCode());
        hash = 31 * hash + (this.invoiceId == null ? 0 : this.invoiceId.hashCode());
        return hash;
    }

    private void setInvoiceId(final String invoiceId){ this.invoiceId = invoiceId;}
    public void setInvoiceStatus(ModelStatus modelStatus) { this.invoiceStatus = modelStatus;}
    public void setInvoiceCustomer(Customer customer){this.customer = customer;}
}
