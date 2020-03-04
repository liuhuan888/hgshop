package com.liuhuan.hgshop.pojo;

import java.io.Serializable;

/**
 * 
 * @author 83795 规格属性
 */
public class SpecOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4791603312456732643L;
	
	private Integer id; // 规格属性id
	private String optionName; // 规格属性名称
	private Integer specId; // 规格id
	private Integer orders = 0; // 排序值

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((optionName == null) ? 0 : optionName.hashCode());
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
		SpecOption other = (SpecOption) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (optionName == null) {
			if (other.optionName != null)
				return false;
		} else if (!optionName.equals(other.optionName))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "SpecOption [id=" + id + ", optionName=" + optionName + ", specId=" + specId + ", orders=" + orders
				+ "]";
	}

	public SpecOption(Integer id, String optionName, Integer specId, Integer orders) {
		super();
		this.id = id;
		this.optionName = optionName;
		this.specId = specId;
		this.orders = orders;
	}

	public SpecOption() {
		super();
	}

}
