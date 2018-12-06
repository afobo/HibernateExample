package com.github.afobo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product implements java.io.Serializable {

	private int rid;
	private int oid;
	private int from_gid;
	private int to_gid;
	private String name;

	public Product() {
	}

	public Product(int rid, int oid, int from_gid, int to_gid, String name) {
		this.rid = rid;
		this.oid = oid;
		this.from_gid = from_gid;
		this.to_gid = to_gid;
		this.name = name;
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
}
