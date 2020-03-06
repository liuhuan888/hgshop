package com.liuhuan.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuhuan.hgshop.pojo.Brand;

public interface BrandDao {

	//品牌列表
	List<Brand> list(@Param("firstChar")String firstChar);

	//添加品牌
	int addBrand(Brand brand);

	//删除品牌
	int deleteBrand(Integer id);

	//修改品牌
	int updateBrand(Brand brand);

	//批量删除品牌
	int deleteBatchBrand(int[] ids);

	//根据id查询品牌（用于修改回显）
	Brand findById(Integer id);

}
