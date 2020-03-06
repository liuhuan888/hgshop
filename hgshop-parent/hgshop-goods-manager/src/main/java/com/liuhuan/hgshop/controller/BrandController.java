package com.liuhuan.hgshop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.service.BrandService;

@Controller
@RequestMapping("brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	
	@RequestMapping("list")
	public String list(Model model,String firstChar,
						@RequestParam(defaultValue="1")int pageNum) {
		PageInfo<Brand> pageInfo = brandService.list(pageNum, firstChar);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("firstChar", firstChar);
		return "brand/list";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(Brand brand) {
		return brandService.addBrand(brand)>0?"success":"false";
	}
	
	@RequestMapping("delBrand")
	@ResponseBody
	public String delBrand(Integer id) {
		return brandService.deleteBrand(id)>0?"success":"false";
	}
	
	@RequestMapping("delBrandBatch")
	@ResponseBody
	public String delBrandBatch(@RequestParam(name="ids[]")int[] ids) {
		return brandService.deleteBatchBrand(ids)>0?"success":"false";
	}
	
	@RequestMapping("getBrand")
	@ResponseBody
	public Brand getBrand(Integer id) {
		return brandService.findById(id);
	}
	
	@RequestMapping("updateBrand")
	@ResponseBody
	public String updateBrand(Brand brand) {
		return brandService.updateBrand(brand)>0?"success":"false";
	}
	
	
}
