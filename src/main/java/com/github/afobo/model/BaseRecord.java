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

import static com.github.afobo.model.Constants.GID_FILTER;
import static com.github.afobo.model.Constants.GID_FILTER_CONDITION;
import static com.github.afobo.model.Constants.GID_FILTER_PARAM;

@MappedSuperclass
@Access(AccessType.FIELD)
@FilterDef(name = GID_FILTER, parameters = {@ParamDef(name = GID_FILTER_PARAM, type = "integer")}, defaultCondition = GID_FILTER_CONDITION)
@Filter(name = GID_FILTER)
public abstract class BaseRecord implements Serializable {

    @Id
    @Column(name = "RID")
    private int recordId;

    @Column(name = "OID")
    private int objectId;

    @Column(name = "FROM_GID")
    private int fromGid;

    @Column(name = "TO_GID")
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
