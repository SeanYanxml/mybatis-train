package com.yanxml.mybatis.train.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanxml.mybatis.train.springboot.bean.User;
import com.yanxml.mybatis.train.springboot.dao.UserDao;

//import com.yanxml.mybatis.train.dynamic.sql.bean.User;
//import com.yanxml.mybatis.train.dynamic.sql.dao.UserDao;
//import com.yanxml.mybatis.train.dynamic.sql.util.SessionUtil;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	// 使用Mybatis新API
	public User getById(Integer id) {
		return userDao.getById(id);
	}

	// 查询List
	public List<User> getList(Map<String,String> map) {
		return userDao.getList(map);
	}
	
	// 插入
	public User insert(User user) {
		userDao.insert(user);
		return user;
	}
	
	// 更新
	public User update(User user) {
		userDao.update(user);
		return user;
	}
	
	// 删除
	public int delete(int id) {
		return userDao.delete(id); 
	}
	
	
	public void test() {		
		// get
		User user1 = getById(1);
		System.out.println("Insert");
		System.out.println(user1);
		
		// getList
		List<User> userList = getList(null);
		System.out.println("GetList");
		for(User user : userList){
			System.out.println(user);
		}
		
		// insert
		User user2 = new User("USERNAME","PWD");
		user2 = insert(user2);
		System.out.println("Insert");
		System.out.println(user2);
		
//		// update
//		user2.setName("UPDATE_USERNAME");
		user2.setPassword("PASSWORD_USERNAME");
		System.out.println("Update");
		System.out.println(update(user2));
		
		// delete
//		System.out.println(delete(user2.getId()));

		
	}

}
