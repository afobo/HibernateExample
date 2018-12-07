package com.github.afobo;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "SERVICES")
@FilterDef(name = "gidFilter", parameters = {@ParamDef(name = "gid", type = "integer")})
@Filter(name = "gidFilter", condition = ":gid between from_gid and to_gid")
public class Service implements java.io.Serializable {

    private int rid;
    private int oid;
    private int from_gid;
    private int to_gid;
    private String name;
    private int product_oid;

    private Set<Product> products;

    public Service() {
    }

    public Service(int rid, int oid, int from_gid, int to_gid, String name, int product_oid) {
        this.rid = rid;
        this.oid = oid;
        this.from_gid = from_gid;
        this.to_gid = to_gid;
        this.name = name;
        this.product_oid = product_oid;
    }

    @Id
    @Column(name = "RID", unique = true, nullable = false, precision = 5, scale = 0)
    public int getRid() {
        return this.rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Column(name = "OID", nullable = false, precision = 5, scale = 0)
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Column(name = "FROM_GID", nullable = false, precision = 5, scale = 0)
    public int getFrom_gid() {
        return from_gid;
    }

    public void setFrom_gid(int from_gid) {
        this.from_gid = from_gid;
    }

    @Column(name = "TO_GID", nullable = false, precision = 5, scale = 0)
    public int getTo_gid() {
        return to_gid;
    }

    public void setTo_gid(int to_gid) {
        this.to_gid = to_gid;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PRODUCT_OID", nullable = false, precision = 5, scale = 0)
    public int getProduct_oid() {
        return product_oid;
    }

    public void setProduct_oid(int product_oid) {
        this.product_oid = product_oid;
    }

    @OneToMany
    @JoinColumn(name = "OID", referencedColumnName = "PRODUCT_OID")
    @Filter(name="gidFilter", condition=":gid between from_gid and to_gid")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
