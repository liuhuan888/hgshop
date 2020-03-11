package com.liuhuan.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuhuan.hgshop.pojo.Spec;
import com.liuhuan.hgshop.pojo.SpecOption;

public interface SpecDao {

	//查询规格列表
	List<Spec> list(@Param("name")String name);

	//添加规格
	int addSpec(Spec spec);

	//添加规格属性
	int addSpecOption(SpecOption option);

	//修改规格
	int updateSpec(Spec spec);

	//删除规格属性
	int deleteSpecOption(Integer id);

	//删除规格
	int deleteSpec(String id);

	//查询一个规格（用于修改时回显）
	Spec findById(Integer id);

	//批量删除
	int deleteSpecBatch(int[] ids);

	//批量删除规格属性
	int deleteSpecOptionBatch(int[] ids);

	//获取所有的规格
	List<Spec> listAll();

}
