package com.github.afobo;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Access(AccessType.FIELD)
@FilterDef(name = "gidFilter", parameters = {@ParamDef(name = "gid", type = "integer")})
@Filter(name = "gidFilter", condition = ":gid between from_gid and to_gid")
public abstract class BaseRecord implements Serializable {

    @Id
    @Column(name = "RID", unique = true, nullable = false, precision = 5, scale = 0)
    private int rid;
    @Column(name = "OID", nullable = false, precision = 5, scale = 0)
    private int oid;
    @Column(name = "FROM_GID", nullable = false, precision = 5, scale = 0)
    private int from_gid;
    @Column(name = "TO_GID", nullable = false, precision = 5, scale = 0)
    private int to_gid;

    public BaseRecord() {
    }

    public BaseRecord(int rid, int oid, int from_gid, int to_gid) {
        this.rid = rid;
        this.oid = oid;
        this.from_gid = from_gid;
        this.to_gid = to_gid;
    }

    public int getRid() {
        return this.rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getFrom_gid() {
        return from_gid;
    }

    public void setFrom_gid(int from_gid) {
        this.from_gid = from_gid;
    }

    public int getTo_gid() {
        return to_gid;
    }

    public void setTo_gid(int to_gid) {
        this.to_gid = to_gid;
    }
}
