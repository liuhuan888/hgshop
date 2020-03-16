package com.liuhuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.dao.BrandDao;
import com.liuhuan.hgshop.dao.CategoryDao;
import com.liuhuan.hgshop.dao.SkuDao;
import com.liuhuan.hgshop.dao.SpuDao;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.pojo.Category;
import com.liuhuan.hgshop.pojo.Sku;
import com.liuhuan.hgshop.pojo.SpecOption;
import com.liuhuan.hgshop.pojo.Spu;
import com.liuhuan.hgshop.pojo.SpuEsVo;
import com.liuhuan.hgshop.pojo.SpuVo;
import com.liuhuan.hgshop.service.GoodsService;
import com.liuhuan.hgshop.utils.ElSearchUtil;

@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SpuDao spuDao;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private BrandDao brandDao;
	@Autowired
	KafkaTemplate<String, String> kafakTemplate;
	// 工具类
	@Autowired
	ElSearchUtil<SpuEsVo> elSearchUtils;
	
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
		return categoryDao.addCategory(category);
	}

	@Override
	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	public int deleteCategory(Integer id) {
		return categoryDao.deleteCategory(id);
	}

	@Override
	public PageInfo<Category> listCategory(Integer pageNum, String firstChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> treeCategory() {
		return categoryDao.tree();
	}

	//Spu的列表
	@Override
	public PageInfo<Spu> listSpu(Integer pageNum, SpuVo spuVo) {
		PageHelper.startPage(pageNum, 10);
		List<Spu> list = spuDao.listSpu(spuVo);
		return new PageInfo<>(list);
	}

	@Override
	public int addSpu(Spu spu) {
		int i = spuDao.addSpu(spu);
		// 将该数据收集到搜搜引擎当中
		Spu newSpu = spuDao.findById(spu.getId());
		SpuEsVo spuEsVo = new SpuEsVo(newSpu);
		System.out.println(" >>>>>>>>>>> spuEsVo is " + spuEsVo);
		elSearchUtils.saveObject(spu.getId().toString(), spuEsVo);
		// 使用kafak 去发送消息  把商品id 发送到主题MyAddSpu 上。
		kafakTemplate.send("1709d", "addspu",spu.getId().toString() );
		return i;
	}

	@Override
	public int updateSpu(Spu spu) {
		return spuDao.updateSpu(spu);
	}

	@Override
	public int deleteSpu(Integer id) {
		return spuDao.deleteSpu(id);
	}

	@Override
	public int deleteSpuBatch(Integer[] ids) {
		return spuDao.deleteSpuBatch(ids);
	}

	//sku列表
	@Override
	public PageInfo<Sku> listSku(int pageNum, Sku sku) {
		PageHelper.startPage(pageNum, 5);
		List<Sku> list = skuDao.list(sku);
		return new PageInfo<>(list);
	}

	@Override
	public int addSku(Sku sku) {
		sku.setStatus(1);
		int i = skuDao.addSku(sku);
		if(i>0) {
			List<SpecOption> specs = sku.getSpecs();
			for (SpecOption specOption : specs) {
				i += skuDao.addSkuSpec(sku.getId(),specOption);
			}
		}
		return i;
	}

	@Override
	public Sku getSku(int id) {
		return skuDao.getSku(id);
	}

	@Override
	public int updateSku(Sku sku) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSku(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSkuBatch(int[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Spu getSpu(int id) {
		return spuDao.findById(id);
	}

	@Override
	public List<Sku> listSkuBySpu(Integer spuId) {
		return skuDao.listBySpu(spuId);
	}

	@Override
	public List<Brand> getAllBrands() {
		return brandDao.listAll();
	}

}
