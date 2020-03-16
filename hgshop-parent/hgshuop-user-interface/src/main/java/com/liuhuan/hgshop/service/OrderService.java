package com.liuhuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Order;
import com.liuhuan.hgshop.pojo.OrderDetail;

/**
 * 订单的服务
 * @author 83795
 *
 */
public interface OrderService {

	/**
	 * 查看订单
	 * @param userId
	 * @param page
	 * @return
	 */
	PageInfo<Order> list(int userId,int pageNum);
	
	/**
	 * 查看订单详情
	 * @param orderId
	 * @param page
	 * @return
	 */
	List<OrderDetail> listDetail(int orderId,int pageNum);
}
