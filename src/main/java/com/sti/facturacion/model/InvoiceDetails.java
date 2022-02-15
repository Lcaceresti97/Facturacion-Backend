package com.sti.facturacion.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sti.facturacion.model.status.ModelStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_invoice_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDetails {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice_detail", nullable = false, unique = true , length = 64)
    private String invoiceDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name= "amount", nullable = false)
    private Integer invoiceDetailAmount;

    @Column(name = "price", nullable = false)
    private Double invoiceDetailPrice;

    @Column(name = "total_parcial", nullable = false)
    private Double invoiceDetailTotalParcial;

    @Column(name = "isv", nullable = false)
    private Double invoiceDetailIsv;

    @Column(name = "discount", nullable = false)
    private Double invoiceDetailDiscount;

    @Column(name = "total", nullable = false)
    private Double invoiceDetailTotal;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus invoiceDetailStatus;

    @JsonBackReference
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Adds fields which are not populated by Invoice DTO.
     * @return
     */
    public static InvoiceDetails buildFromDtoInvoiceDetails(InvoiceDetails invoiceDetails){
        invoiceDetails.setInvoiceDetailId(UUID.randomUUID().toString());
        invoiceDetails.setInvoiceDetailStatus(ModelStatus.ACTIVE);
        return invoiceDetails;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        InvoiceDetails invoiceDetails = (InvoiceDetails) o;
        return this.invoiceDetailId == invoiceDetails.invoiceDetailId
                && (this.invoiceDetailAmount.equals(invoiceDetails.invoiceDetailAmount));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.invoiceDetailAmount == null ? 0 : this.invoiceDetailAmount.hashCode());
        hash = 31 * hash + (this.invoiceDetailTotalParcial == null ? 0 : this.invoiceDetailTotalParcial.hashCode());
        hash = 31 * hash + (this.invoiceDetailIsv== null ? 0 : this.invoiceDetailIsv.hashCode());
        hash = 31 * hash + (this.invoiceDetailDiscount== null ? 0 : this.invoiceDetailDiscount.hashCode());
        hash = 31 * hash + (this.invoiceDetailTotal== null ? 0 : this.invoiceDetailTotal.hashCode());
        return hash;
    }

    protected void setInvoiceDetail(String invoiceDetailId){this.invoiceDetailId = invoiceDetailId;}
    public void setInvoiceDetailStatus(ModelStatus modelStatus){this.invoiceDetailStatus = modelStatus;}
    public void setInvoiceDetailInvoice(Invoice invoice){this.invoice = invoice;}

}
