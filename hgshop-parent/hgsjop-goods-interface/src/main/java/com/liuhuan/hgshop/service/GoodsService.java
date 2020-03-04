package com.liuhuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.pojo.Category;

/**
 * 
 * @author 83795
 *	Dubbo 服务接口函数必须要有非void返回值
 */
public interface GoodsService {

	int addBrand(Brand brand);
	int updateBrand(Brand brand);
	int deleteBrand(Integer id);
	
	/**
	 * 
	 * @param pageNum 页码
	 * @param firstChar 首字母
	 * @return
	 */
	PageInfo<Brand> list(Integer pageNum,String firstChar);
	
	int addCategory(Category category);
	int updateCategory(Category category);
	int deleteCategory(Integer id);
	
	/**
	 * 
	 * @param pageNum 页码
	 * @param firstChar 首字母
	 * @return
	 */
	PageInfo<Category> listCategory(Integer pageNum,String firstChar);
	
	List<Category> treeCategory();
	
}
