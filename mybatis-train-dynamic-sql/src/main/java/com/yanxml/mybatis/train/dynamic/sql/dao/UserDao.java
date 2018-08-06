package com.yanxml.mybatis.train.dynamic.sql.dao;

import java.util.List;
import java.util.Map;

import com.yanxml.mybatis.train.dynamic.sql.bean.User;

public interface UserDao {
	// 根据id查询
	public User getById(int id);
	// 查询List
	public List<User> getList(Map<String,String> map);
	// 插入
	public int insert(User user);
	// 更新
	public int update(User user);
	// 删除
	public int delete(int id);
}
