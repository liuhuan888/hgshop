package com.liuhuan.hgshop.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuhuan.hgshop.config.AdminProperties;
import com.liuhuan.hgshop.service.UserService;

/**
 * 
 * @author 83795
 *
 */
@Service(interfaceClass = UserService.class,version="1.0.0")
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminProperties adminProperties;
	
	@Override
	public boolean login(String userName, String password) {
		return adminProperties.getAdminUser().equals(userName) 
				&& adminProperties.getAdminPassword().equals(password);
	}

}
