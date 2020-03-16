package com.liuhuan.hgshop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单详情
 * @author 83795
 *
 */
public class OrderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7266877164437074106L;
	
	private Integer id;
	private Integer skuid;
	private BigDecimal total; //该条的价格
	private Integer oid;
	private Integer pnum;
	private Sku sku;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", skuid=" + skuid + ", total=" + total + ", oid=" + oid + ", pnum=" + pnum
				+ ", sku=" + sku + "]";
	}

	public OrderDetail(Integer oid, Cart cart) {
		super();
		this.oid = oid;
		this.pnum = cart.getPnum();
		this.total = cart.getSumTotal();
		this.skuid = cart.getSkuid();
	}

	public OrderDetail() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}

}
