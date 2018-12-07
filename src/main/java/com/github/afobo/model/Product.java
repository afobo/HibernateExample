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
    @Filter(name="gidFilter", condition=":gid between from_gid and to_gid")
    private Set<Service> services = new HashSet<>();

    public Product() {
    }

    public Product(int rid, int oid, int from_gid, int to_gid, String name) {
        super(rid, oid, from_gid, to_gid);
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
