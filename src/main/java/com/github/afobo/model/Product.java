package com.github.afobo.model;

import org.hibernate.annotations.Filter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
@Access(AccessType.FIELD)
public class Product extends BaseRecord {

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;
    @OneToMany
    @JoinColumn(name = "PRODUCT_OID", referencedColumnName = "OID")
    @Filter(name=GID_FILTER_NAME, condition=GID_FILTER_CONDITION)
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
