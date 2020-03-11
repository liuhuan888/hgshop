package com.liuhuan.hgshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Sku;
import com.liuhuan.hgshop.pojo.Spec;
import com.liuhuan.hgshop.pojo.SpecOption;
import com.liuhuan.hgshop.pojo.Spu;
import com.liuhuan.hgshop.pojo.SpuVo;
import com.liuhuan.hgshop.service.GoodsService;
import com.liuhuan.hgshop.service.SpecService;

@Controller
@RequestMapping("goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	
	@Reference
	private SpecService specService;
	
	/**
	 * spu列表
	 * @param model
	 * @param spuVo
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model,SpuVo spuVo,
						@RequestParam(defaultValue="1")Integer pageNum) {
		PageInfo<Spu> pageInfo = goodsService.listSpu(pageNum, spuVo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("spuVo", spuVo);
		return "/goods/list";
	}
	
	
	
	/**
	 * sku列表
	 * @param model
	 * @param sku
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("skulist")
	public String skulisy(Model model,Sku sku,
			@RequestParam(defaultValue="1")Integer pageNum) {
		PageInfo<Sku> pageInfo = goodsService.listSku(pageNum, sku);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("sku", sku);
		return "sku/list";
	}
	
	//详情查询
	@RequestMapping("skuDetail")
	public String skuDetail(Model model,Integer id) {
		Sku sku = goodsService.getSku(id);
		model.addAttribute("sku", sku);
		return "sku/detail";
	}
	
	//跳转到sku添加页面
	@RequestMapping("toaddSku")
	public String toaddSku(Model model,Integer spuId) {
		// 获取要添加的商品
		Spu spu = goodsService.getSpu(spuId);
		model.addAttribute("spu", spu);
		// 属性名称
		List<Spec> list = specService.listAll();
		model.addAttribute("specs", list);
		return "sku/add";
	}
	
	//添加sku
	@RequestMapping("addSku")
	@ResponseBody
	public String addSku(Sku sku,int[] specIds,@RequestParam(value="specOptionIds") int[] specOptionIds) {
		// 保存给sku的所有的属性以及属性值
		ArrayList<SpecOption> specs = new ArrayList<>();
		for (int i = 0; i < specIds.length && i<specOptionIds.length; i++) {
			SpecOption option = new SpecOption();
			// 规格的id
			option.setSpecId(specIds[i]);
			// 具体的属性值 的id
			option.setId(specOptionIds[i]);
			specs.add(option);
		}
		//存放规格的数据
		sku.setSpecs(specs);
		int i = goodsService.addSku(sku);
		return i>0?"success":"false";
	}
	
	
}
