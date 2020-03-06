package com.liuhuan.hgshop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Spec;
import com.liuhuan.hgshop.service.SpecService;

@Controller
@RequestMapping("spec")
public class SpecController {

	@Reference
	private SpecService specService;
	
	/**
	 * 进入规格的列表
	 * @param model
	 * @param name
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model,String name,
						@RequestParam(defaultValue="1")int pageNum) {
		System.out.println(name+"=="+pageNum);
		PageInfo<Spec> pageInfo = specService.list(name, pageNum);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("name", name);
		return "spec/list";
	}
	
	/**
	 * 添加
	 * @param spec
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String addSpec(Spec spec) {
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
		int i = specService.addSpec(spec);
		return i>0?"success":"false";
	}
	
	/**
	 * 删除规格
	 * @param id
	 * @return
	 */
	@RequestMapping("delSpec")
	@ResponseBody
	public String delSpec(String id) {
		int i = specService.deleteSpec(id);
		return i>0?"success":"false";
	}
	
	/** 
	 * 批量删除规格
	 * @param ids
	 * @return
	 */
	@RequestMapping("delSpecBatch")
	@ResponseBody
	public String delSpecBatch(@RequestParam(name="ids[]")int[] ids) {
		int i = specService.deleteSpecBatch(ids);
		return i>0?"success":"false";
	}
}
