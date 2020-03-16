package com.liuhuan.hgshop.dao;

import java.util.List;

import com.liuhuan.hgshop.pojo.Cart;

public interface CartDao {

	int add(Cart cart);

	int delete(int[] ids);

	int clear(int uid);

	List<Cart> list(int uid);

	List<Cart> listByIds(int[] cartIds);

	// 从购物车中删除这些数据
	void deleteByIds(int[] cartIds);

}
