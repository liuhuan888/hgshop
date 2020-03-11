package com.liuhuan.hgshop.dao;

import java.util.List;

import com.liuhuan.hgshop.pojo.Category;

public interface CategoryDao {

	//树形列表
	List<Category> tree();

	//添加子分类
	int addCategory(Category category);

	//删除分类
	int deleteCategory(Integer id);

	//修改分类
	int updateCategory(Category category);
	

}
