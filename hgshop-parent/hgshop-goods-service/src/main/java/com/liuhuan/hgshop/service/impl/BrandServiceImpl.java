package com.liuhuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.dao.BrandDao;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.service.BrandService;
@Service(interfaceClass=BrandService.class)
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;
	
	@Override
	public PageInfo<Brand> list(Integer pageNum, String firstChar) {
		PageHelper.startPage(pageNum, 3);
		List<Brand> list = brandDao.list(firstChar);
		return new PageInfo<>(list);
	}

	@Override
	public int addBrand(Brand brand) {
		return brandDao.addBrand(brand);
	}

	@Override
	public int deleteBrand(Integer id) {
		return brandDao.deleteBrand(id);
	}

	@Override
	public int updateBrand(Brand brand) {
		return brandDao.updateBrand(brand);
	}

	@Override
	public int deleteBatchBrand(int[] ids) {
		return brandDao.deleteBatchBrand(ids);
	}

	@Override
	public Brand findById(Integer id) {
		return brandDao.findById(id);
	}

}
