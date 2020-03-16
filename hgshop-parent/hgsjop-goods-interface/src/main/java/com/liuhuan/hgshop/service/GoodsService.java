package com.liuhuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.pojo.Category;
import com.liuhuan.hgshop.pojo.Sku;
import com.liuhuan.hgshop.pojo.Spu;
import com.liuhuan.hgshop.pojo.SpuVo;

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
	
	//spu管理
	PageInfo<Spu> listSpu(Integer pageNum,SpuVo spuVo);
	int addSpu(Spu spu);
	int updateSpu(Spu spu);
	int deleteSpu(Integer id);
	int deleteSpuBatch(Integer[] ids);
	Spu getSpu(int id);
	List<Brand> getAllBrands();
	
	// sku的管理
	PageInfo<Sku>  listSku(int pageNum,Sku sku);
	int addSku(Sku sku);
	//获取详情
	Sku getSku(int id);
	int updateSku(Sku sku);
	int deleteSku(int id);
	int deleteSkuBatch(int[] id);
	//根据spuId查询sku
	List<Sku> listSkuBySpu(Integer spuId);
}
