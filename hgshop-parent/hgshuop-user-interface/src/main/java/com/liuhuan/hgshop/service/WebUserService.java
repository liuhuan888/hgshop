package com.liuhuan.hgshop.service;

import com.liuhuan.hgshop.pojo.User;

/**
 * 
 * @author 83795
 *
 */
public interface WebUserService {

	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	User login(String userName,String password);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	User register(User user);
}
