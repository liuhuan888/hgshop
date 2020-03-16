package com.liuhuan.hgshop.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.common.HgShopConstant;
import com.liuhuan.hgshop.pojo.Category;
import com.liuhuan.hgshop.pojo.Sku;
import com.liuhuan.hgshop.pojo.Spu;
import com.liuhuan.hgshop.pojo.SpuEsVo;
import com.liuhuan.hgshop.pojo.SpuVo;
import com.liuhuan.hgshop.service.GoodsService;
import com.liuhuan.hgshop.utils.ElSearchUtil;

/**
 * 首页
 * @author 83795
 *
 */
@Controller
public class IndexController {

	@Reference
	private GoodsService goodsService;
	
	// 搜索引擎
	@Autowired 
	ElSearchUtil<SpuEsVo> elUtils;
	
	@Autowired
	private RedisTemplate<String, PageInfo<Spu>> redisTemplate;
	
	/**
	 * 首页
	 * @param model
	 * @param pageNum
	 * @param catId
	 * @return
	 */
	@RequestMapping({"/","index"})
	public String index(Model model,
			@RequestParam(defaultValue="1") int pageNum,
			@RequestParam(defaultValue="0") int catId) {
		// 获取商品的数据
		// 只有首页的数据才缓存
		if(pageNum==1 && catId==0) {
			ValueOperations<String, PageInfo<Spu>> opsForValue = redisTemplate.opsForValue();
			//有缓存
			if(redisTemplate.hasKey(HgShopConstant.SPU_CACHE_KEY)) {
				PageInfo<Spu> pageInfo = opsForValue.get(HgShopConstant.SPU_CACHE_KEY);
				model.addAttribute("pageInfo", pageInfo);
				return "index";
			}else {
				// 从服务中获取数据
				PageInfo<Spu> pageInfo = goodsService.listSpu(1, new SpuVo());
				// 数据放入缓存当中
				//缓存30分钟
				opsForValue.set(HgShopConstant.SPU_CACHE_KEY, pageInfo,30,TimeUnit.MINUTES);
				model.addAttribute("pageInfo", pageInfo);
				return "index";
			}
		}
		PageInfo<Spu> pageInfo = goodsService.listSpu(pageNum, new SpuVo());
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	@RequestMapping("detail")
	public String detail(Model model,Integer spuId) {
		//获取详情
		Spu spu = goodsService.getSpu(spuId);
		List<Sku> skus = goodsService.listSkuBySpu(spuId);
		model.addAttribute("spu", spu);
		model.addAttribute("skus", skus);
		return "detail";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("search")
	public String search(HttpServletRequest request,
			String key,@RequestParam(defaultValue="1") int page) {
		
		AggregatedPage<SpuEsVo> aggregatedPage = elUtils.queryObjects(SpuEsVo.class, page, 10, new String[]{"goodsName","caption","brandName","categoryName"},key );
		//aggregatedPage.getContent();
		request.setAttribute("pageInfo", aggregatedPage);
		return "search";
		
	}
	
	
	@RequestMapping("treeData")
	@ResponseBody
	public List<Category> treeData(HttpServletRequest request) {
		return goodsService.treeCategory();
	}
}
