package com.liuhuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.pojo.Category;
import com.liuhuan.hgshop.service.GoodsService;

@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {

	@Override
	public int addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBrand(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<Brand> list(Integer pageNum, String firstChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<Category> listCategory(Integer pageNum, String firstChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> treeCategory() {
		// TODO Auto-generated method stub
		return null;
	}

}