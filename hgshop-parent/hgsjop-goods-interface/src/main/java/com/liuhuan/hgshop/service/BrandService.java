package com.liuhuan.hgshop.service;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Brand;

public interface BrandService {

	//品牌列表
	PageInfo<Brand> list(Integer pageNum,String firstChar);
	
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
