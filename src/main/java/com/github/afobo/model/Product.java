package com.github.afobo.model;

import org.hibernate.annotations.Filter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.github.afobo.model.Constants.GID_FILTER;

@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseRecord {

    @Column(name = "NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "PRODUCT_OID", referencedColumnName = "OID")
    @Filter(name = GID_FILTER)
    private Set<Service> services = new HashSet<>();

    public Product() {
    }

    public Product(int recordId, int objectId, int fromGid, int toGid, String name) {
        super(recordId, objectId, fromGid, toGid);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
