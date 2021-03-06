package com.liuhuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Spec;

/**
 * 
 * @author 83795
 *
 */
public interface SpecService {

	//查询规格列表
	PageInfo<Spec> list(String name,Integer pageNum);
	
	//添加规格
	int addSpec(Spec spec);
	
	//修改规格
	int updateSpec(Spec spec);
	
	//删除规格
	int deleteSpec(String id);
	
	//查询一个规格（用于修改时回显）
	Spec findById(Integer id);

	//批量删除
	int deleteSpecBatch(int[] ids);

	//获取所有规格
	List<Spec> listAll();
	
}
