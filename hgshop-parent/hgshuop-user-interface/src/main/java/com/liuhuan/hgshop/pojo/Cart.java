package com.liuhuan.hgshop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车
 * 
 * @author 83795
 *
 */
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8839477513056591094L;
	
	private Integer id;
	private Integer uid;
	private Integer pnum;// 购买数量
	private Integer skuid;
	private Date createtime;
	private Date updatetime;
	private BigDecimal sumTotal; // 总价
	private String title; // sku 的名称
	private Sku sku;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(BigDecimal sumTotal) {
		this.sumTotal = sumTotal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", uid=" + uid + ", pnum=" + pnum + ", skuid=" + skuid + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", sumTotal=" + sumTotal + ", title=" + title + ", sku=" + sku + "]";
	}

	public Cart(Integer uid, Integer pnum, Integer skuid) {
		super();
		this.uid = uid;
		this.pnum = pnum;
		this.skuid = skuid;
	}

	public Cart() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((skuid == null) ? 0 : skuid.hashCode());
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
		Cart other = (Cart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (skuid == null) {
			if (other.skuid != null)
				return false;
		} else if (!skuid.equals(other.skuid))
			return false;
		return true;
	}

}
