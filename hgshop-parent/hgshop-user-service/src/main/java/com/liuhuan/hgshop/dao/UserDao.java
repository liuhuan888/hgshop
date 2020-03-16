package com.liuhuan.hgshop.dao;

import org.apache.ibatis.annotations.Param;

import com.liuhuan.hgshop.pojo.User;

public interface UserDao {

	//用户登录
	User findUser(@Param("userName")String userName, @Param("password")String password);

	//用户注册
	int register(User user);

	//根据id查询用户
	User findById(Integer uid);

}
