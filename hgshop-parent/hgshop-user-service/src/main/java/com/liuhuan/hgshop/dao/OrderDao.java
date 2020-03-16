package com.liuhuan.hgshop.dao;

import java.util.List;

import com.liuhuan.hgshop.pojo.Order;
import com.liuhuan.hgshop.pojo.OrderDetail;

public interface OrderDao {

	int add(Order order);

	// 插入子表
	int addDetail(OrderDetail orderDetail);

	List<Order> list(int userId);

	List<OrderDetail> listDetail(int orderId);

}
