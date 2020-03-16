package com.liuhuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.dao.OrderDao;
import com.liuhuan.hgshop.pojo.Order;
import com.liuhuan.hgshop.pojo.OrderDetail;
import com.liuhuan.hgshop.service.OrderService;

/**
 * 
 * @author 83795
 *
 */
@Service(interfaceClass=OrderService.class)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public PageInfo<Order> list(int userId, int pageNum) {
		PageHelper.startPage(pageNum, 10);

		return new PageInfo<Order>(orderDao.list(userId));
	}

	@Override
	public List<OrderDetail> listDetail(int orderId, int pageNum) {
		return orderDao.listDetail(orderId);
	}

}
