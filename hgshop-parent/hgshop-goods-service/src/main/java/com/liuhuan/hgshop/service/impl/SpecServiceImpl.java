package com.liuhuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.dao.SpecDao;
import com.liuhuan.hgshop.pojo.Spec;
import com.liuhuan.hgshop.pojo.SpecOption;
import com.liuhuan.hgshop.service.SpecService;
@Service(interfaceClass=SpecService.class)
public class SpecServiceImpl implements SpecService{

	@Autowired
	private SpecDao specDao;
	
	//查询规格列表
	@Override
	public PageInfo<Spec> list(String name, Integer pageNum) {
		PageHelper.startPage(pageNum, 3);
		List<Spec> list = specDao.list(name);
		return new PageInfo<>(list);
	}

	//添加规格
	@Override
	public int addSpec(Spec spec) {
		int i = specDao.addSpec(spec);
		if(i>0) {
			List<SpecOption> options = spec.getOptions();
			for (SpecOption option : options) {
				option.setSpecId(spec.getId());
				i = specDao.addSpecOption(option);
			}
		}
		return i;
	}

	//修改规格
	@Override
	public int updateSpec(Spec spec) {
		return specDao.updateSpec(spec);
	}

	//删除规格
	@Override
	public int deleteSpec(String id) {
		int i = specDao.deleteSpecOption(Integer.parseInt(id));
		if(i>0) {
			i = specDao.deleteSpec(id);
		}
		return i;
	}

	//查询一个规格（用于修改时回显）
	@Override
	public Spec findById(Integer id) {
		return specDao.findById(id);
	}

	@Override
	public int deleteSpecBatch(int[] ids) {
		int i = specDao.deleteSpecOptionBatch(ids);
		i=specDao.deleteSpecBatch(ids);
		return i;
	}

	//获取所有的规格
	@Override
	public List<Spec> listAll() {
		
		return specDao.listAll();
	}

	
}
