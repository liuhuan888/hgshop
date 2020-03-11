package com.liuhuan.hgshop.pojo;

import java.io.Serializable;

/**
 * 商品管理
 * 
 * @author 83795
 *
 */
public class Spu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8131450169816060156L;
	private Integer id;
	private String goodsName;
	private String isMarkerable; // 是否上线
	private Integer brandId; // 品牌
	private String caption; // 标题
	private Integer categoryId; // 分类
	private String smallPic; // 小图

	private Brand brand;
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getIsMarkerable() {
		return isMarkerable;
	}

	public void setIsMarkerable(String isMarkerable) {
		this.isMarkerable = isMarkerable;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Spu [id=" + id + ", goodsName=" + goodsName + ", isMarkerable=" + isMarkerable + ", brandId=" + brandId
				+ ", caption=" + caption + ", categoryId=" + categoryId + ", smallPic=" + smallPic + ", brand=" + brand
				+ ", category=" + category + "]";
	}

	public Spu(Integer id, String goodsName, String isMarkerable, Integer brandId, String caption, Integer categoryId,
			String smallPic, Brand brand, Category category) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.isMarkerable = isMarkerable;
		this.brandId = brandId;
		this.caption = caption;
		this.categoryId = categoryId;
		this.smallPic = smallPic;
		this.brand = brand;
		this.category = category;
	}

	public Spu() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsName == null) ? 0 : goodsName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Spu other = (Spu) obj;
		if (goodsName == null) {
			if (other.goodsName != null)
				return false;
		} else if (!goodsName.equals(other.goodsName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
