package com.liuhuan.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuhuan.hgshop.pojo.Sku;
import com.liuhuan.hgshop.pojo.SpecOption;

public interface SkuDao {

	//sku列表
	List<Sku> list(Sku sku);

	//添加sku
	int addSku(Sku sku);

	//添加商品属性
	int addSkuSpec(@Param("skuId") int skuId,@Param("so") SpecOption so);

	//根据id查询sku
	Sku getSku(int id);

}
