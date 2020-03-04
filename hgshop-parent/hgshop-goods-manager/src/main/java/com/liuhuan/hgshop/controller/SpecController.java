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
	
	@RequestMapping("list")
	public String list(Model model,String name,
						@RequestParam(defaultValue="1")int pageNum) {
		PageInfo<Spec> pageInfo = specService.list(name, pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "spec/list";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String addSpec(Spec spec) {
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
		int i = specService.addSpec(spec);
		return i>0?"success":"false";
	}
}
