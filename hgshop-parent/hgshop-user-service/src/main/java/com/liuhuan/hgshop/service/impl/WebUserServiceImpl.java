package com.liuhuan.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuhuan.hgshop.dao.UserDao;
import com.liuhuan.hgshop.pojo.User;
import com.liuhuan.hgshop.service.WebUserService;

/**
 * 普通用户的服务
 * @author 刘欢
 *
 */
@Service(interfaceClass=WebUserService.class)
public class WebUserServiceImpl implements WebUserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 用户登录
	 */
	@Override
	public User login(String userName, String password) {
		//密码加密
		String pwd = DigestUtils.md5Hex(password);
		return userDao.findUser(userName,pwd);
	}

	/**
	 * 用户注册
	 */
	@Override
	public User register(User user) {
		// 密码加密
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		//如果注册成功
		if(userDao.register(user)>0) {
			//根据id查询用户
			return userDao.findById(user.getUid());
		}
		return null;
	}

}
