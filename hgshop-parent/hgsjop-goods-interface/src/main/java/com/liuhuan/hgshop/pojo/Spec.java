package com.liuhuan.hgshop.pojo;

import java.io.Serializable;
/**
 * 
 * @author 83795
 *规格
 */
import java.util.List;

public class Spec implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365490409092407569L;
	
	private Integer id; // 规格id
	private String specName; // 规格名称

	private List<SpecOption> options; //规格所有的属性

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public List<SpecOption> getOptions() {
		return options;
	}

	public void setOptions(List<SpecOption> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Spec [id=" + id + ", specName=" + specName + ", options=" + options + "]";
	}

	public Spec(Integer id, String specName, List<SpecOption> options) {
		super();
		this.id = id;
		this.specName = specName;
		this.options = options;
	}

	public Spec() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((specName == null) ? 0 : specName.hashCode());
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
		Spec other = (Spec) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (specName == null) {
			if (other.specName != null)
				return false;
		} else if (!specName.equals(other.specName))
			return false;
		return true;
	}

}
