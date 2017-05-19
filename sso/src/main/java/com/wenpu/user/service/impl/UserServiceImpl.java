package com.wenpu.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mybatis.pagination.Pagination;

import org.springframework.stereotype.Service;

import com.wenpu.user.entity.User;
import com.wenpu.user.mapper.UserMapper;
import com.wenpu.user.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Resource(name = "userMapper")
	UserMapper userMapper;

	@Override
	public List<User> getUserList(Pagination pagination,
			Map<String, Object> conditions) {
		// TODO conditions 演示就不处理了
		return userMapper.selectListByPagination(pagination, conditions);
	}

	@Override
	public User getUserById( Long id ) {
		return userMapper.selectById(id);
	}

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.selectByUserName(userName);
	}

}
