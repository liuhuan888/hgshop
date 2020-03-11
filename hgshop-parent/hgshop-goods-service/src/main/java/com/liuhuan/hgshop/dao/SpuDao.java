package com.liuhuan.hgshop.dao;

import java.util.List;

import com.liuhuan.hgshop.pojo.Spu;
import com.liuhuan.hgshop.pojo.SpuVo;

public interface SpuDao {

	List<Spu> listSpu(SpuVo spuVo);

	int addSpu(Spu spu);

	int updateSpu(Spu spu);

	int deleteSpu(Integer id);

	int deleteSpuBatch(Integer[] ids);

	Spu findById(int id);

}
