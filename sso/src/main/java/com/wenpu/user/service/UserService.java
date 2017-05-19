package com.wenpu.user.service;

import java.util.List;
import java.util.Map;

import com.wenpu.user.entity.User;

import mybatis.pagination.Pagination;

public interface UserService {
	
	List<User> getUserList(Pagination pagination, Map<String, Object> conditions);

	User getUserById( Long id );
	
	User getUserByName(String userName);
}
