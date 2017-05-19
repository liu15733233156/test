package com.wenpu.user.mapper;

import java.util.List;
import java.util.Map;

import mybatis.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.wenpu.user.entity.User;


/**
 * UserMapper数据库操作接口类
 */
public interface UserMapper {

	/**
	 * 分页
	 * @param pagination
	 * @param conditions
	 * 				查询条件【可以随便自定义】
	 * @return
	 */
	List<User> selectListByPagination(Pagination pagination,
			Map<String, Object> conditions);

	/**
	 * 查询（根据主键ID查询）
	 */
	User selectById( @Param("id") Long id );
	
	/**
	 * 查询（根据主键ID查询）
	 */
	User selectByUserName( @Param("userName") String userName );


	/**
	 * 删除（根据主键ID删除）
	 */
	int deleteById( @Param("id") Long id );


	/**
	 * 添加
	 */
	int insert( User record );


	/**
	 * 添加 （匹配有值的字段）
	 */
	int insertSelective( User record );


	/**
	 * 修改 （匹配有值的字段）
	 */
	int updateByIdSelective( User record );


	/**
	 * 修改（根据主键ID修改）
	 */
	int updateById( User record );

}