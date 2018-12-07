package com.github.afobo.model;

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
@Filter(name = BaseRecord.GID_FILTER_NAME, condition = BaseRecord.GID_FILTER_CONDITION)
public abstract class BaseRecord implements Serializable {

    public static final String GID_FILTER_NAME = "gidFilter";
    public static final String GID_FILTER_CONDITION = ":gid between from_gid and to_gid";

    @Id
    @Column(name = "RID", unique = true, nullable = false, precision = 5, scale = 0)
    private int recordId;
    @Column(name = "OID", nullable = false, precision = 5, scale = 0)
    private int objectId;
    @Column(name = "FROM_GID", nullable = false, precision = 5, scale = 0)
    private int fromGid;
    @Column(name = "TO_GID", nullable = false, precision = 5, scale = 0)
    private int toGid;

    public BaseRecord() {
    }

    public BaseRecord(int recordId, int objectId, int fromGid, int toGid) {
        this.recordId = recordId;
        this.objectId = objectId;
        this.fromGid = fromGid;
        this.toGid = toGid;
    }

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getFromGid() {
        return fromGid;
    }

    public void setFromGid(int fromGid) {
        this.fromGid = fromGid;
    }

    public int getToGid() {
        return toGid;
    }

    public void setToGid(int toGid) {
        this.toGid = toGid;
    }
}
