package com.sti.facturacion.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sti.facturacion.model.status.ModelStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "id_product", nullable = false, unique = true, length = 64)
    private String productId;

    @Column(name="product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private Double productPrice;

    @Column(name = "stock", nullable = false)
    private Integer productStock;

    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus productStatus;

    /**
     * Adds fields which are not populated by Product DTO.
     * @return
     */
    public static Product buildFromDtoProduct(Product product){
        product.setProductId(UUID.randomUUID().toString());
        product.setProductStatus(ModelStatus.ACTIVE);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Product product = (Product) o;
        return this.productId == product.productId
                && (this.productName.equals(product.productName))
                && (this.productStock.equals(product.productStock));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.productName == null ? 0 : this.productName.hashCode());
        hash = 31 * hash + (this.productId == null ? 0 : this.productId.hashCode());
        hash = 31 * hash + (this.productStock == null ? 0 : this.productStock.hashCode());
        return hash;
    }

    private void setProductId(final String productId){ this.productId = productId;}
    public void setProductStatus(ModelStatus modelStatus) { this.productStatus = modelStatus;}


}
