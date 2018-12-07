package com.github.afobo.model;

import org.hibernate.annotations.Filter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SERVICES")
@Access(AccessType.FIELD)
public class Service extends BaseRecord {

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;
    @Column(name = "PRODUCT_OID", nullable = false, precision = 5, scale = 0)
    private int productObjectId;
    @OneToMany
    @JoinColumn(name = "OID", referencedColumnName = "PRODUCT_OID")
    @Filter(name=GID_FILTER_NAME, condition=GID_FILTER_CONDITION)
    private Set<Product> products  = new HashSet<>();

    public Service() {
    }

    public Service(int recordId, int objectId, int fromGid, int toGid, String name, int productObjectId) {
        super(recordId, objectId, fromGid, toGid);
        this.name = name;
        this.productObjectId = productObjectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductObjectId() {
        return productObjectId;
    }

    public void setProductObjectId(int productObjectId) {
        this.productObjectId = productObjectId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return getProducts().stream().findFirst().orElse(null);
    }

    public void setProduct(Product product) {
        setProducts(Collections.singleton(product));
    }
}
