package com.github.afobo;

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
    private int product_oid;
    @OneToMany
    @JoinColumn(name = "OID", referencedColumnName = "PRODUCT_OID")
    @Filter(name="gidFilter", condition=":gid between from_gid and to_gid")
    private Set<Product> products  = new HashSet<>();

    public Service() {
    }

    public Service(int rid, int oid, int from_gid, int to_gid, String name, int product_oid) {
        super(rid, oid, from_gid, to_gid);
        this.name = name;
        this.product_oid = product_oid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduct_oid() {
        return product_oid;
    }

    public void setProduct_oid(int product_oid) {
        this.product_oid = product_oid;
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
