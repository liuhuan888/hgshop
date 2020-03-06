package com.liuhuan.hgshop.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhuan.hgshop.pojo.Category;
import com.liuhuan.hgshop.service.GoodsService;

@Controller
@RequestMapping("cat")
public class CategoryController {

	@Reference
	private GoodsService goodsService;
	
	@RequestMapping("list")
	public String list() {
		return "cat/list";
	}
	
	@RequestMapping("treeData")
	@ResponseBody
	public List<Category> treeData(){
		return goodsService.treeCategory();
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(Integer parentId,String name) {
		Category category = new Category();
		category.setParentId(parentId);
		category.setName(name);
		return goodsService.addCategory(category)>0?"success":"false";
	}
	
	@RequestMapping("updCategory")
	@ResponseBody
	public String updCategory(Integer id,String name) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		return goodsService.updateCategory(category)>0?"success":"false";
	}
	
	@RequestMapping("delCategory")
	@ResponseBody
	public String delCategory(Integer id) {
		return goodsService.deleteCategory(id)>0?"success":"false";
	}
}
