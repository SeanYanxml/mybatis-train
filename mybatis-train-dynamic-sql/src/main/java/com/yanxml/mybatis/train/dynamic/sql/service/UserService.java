package com.yanxml.mybatis.train.dynamic.sql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yanxml.mybatis.train.dynamic.sql.bean.User;
import com.yanxml.mybatis.train.dynamic.sql.dao.UserDao;
import com.yanxml.mybatis.train.dynamic.sql.util.SessionUtil;

public class UserService {

	// 使用Mybatis新API
	public User getById(Integer id) {
		User resultUser = null;
		SqlSession session = SessionUtil.getSession();
		try {
			// 注意 这里需要通过 相同的映射名称进行映射(即UserDaoMapper.xml文件的namespace名称与
			// UserDao.class的 namespace一致)
			UserDao userDao = session.getMapper(UserDao.class);
			resultUser = userDao.getById(id);
		} finally {
			session.close();
		}
		return resultUser;
	}

	// 查询List
	public List<User> getList(Map<String,String> map) {
		List<User> resultUser = new ArrayList<User>();
		SqlSession session = SessionUtil.getSession();
		try {
			// 注意 这里需要通过 相同的映射名称进行映射(即UserDaoMapper.xml文件的namespace名称与
			// UserDao.class的 namespace一致)
			UserDao userDao = session.getMapper(UserDao.class);
			resultUser = userDao.getList(map);
		} finally {
			session.close();
		}
		return resultUser;
	}
	
	// 插入
	public User insert(User user) {
		SqlSession session = SessionUtil.getSession();
		try {
			// 注意 这里需要通过 相同的映射名称进行映射(即UserDaoMapper.xml文件的namespace名称与
			// UserDao.class的 namespace一致)
			UserDao userDao = session.getMapper(UserDao.class);
			userDao.insert(user);
		} finally {
			session.close();
		}
		return user;
	}
	
	// 更新
	public User update(User user) {
		SqlSession session = SessionUtil.getSession();
		try {
			// 注意 这里需要通过 相同的映射名称进行映射(即UserDaoMapper.xml文件的namespace名称与
			// UserDao.class的 namespace一致)
			UserDao userDao = session.getMapper(UserDao.class);
			 userDao.update(user);
		} finally {
			session.close();
		}
		return user;
	}
	
	// 删除
	public int delete(int id) {
		int flag = 1;
		SqlSession session = SessionUtil.getSession();
		try {
			// 注意 这里需要通过 相同的映射名称进行映射(即UserDaoMapper.xml文件的namespace名称与
			// UserDao.class的 namespace一致)
			UserDao userDao = session.getMapper(UserDao.class);
			flag = userDao.delete(flag);
		} finally {
			session.close();
		}
		return flag;
	}
	
	public static void main(String[] args) {
		UserService userService = new UserService();
		
		// get
		User user1 = userService.getById(1);
		System.out.println("Insert");
		System.out.println(user1);
		
		// getList
		List<User> userList = userService.getList(null);
		System.out.println("GetList");
		for(User user : userList){
			System.out.println(user);
		}
		
		// insert
		User user2 = new User("USERNAME","PWD");
		user2 = userService.insert(user2);
		System.out.println("Insert");
		System.out.println(user2);
		
//		// update
//		user2.setName("UPDATE_USERNAME");
		user2.setPassword("PASSWORD_USERNAME");
		System.out.println("Update");
		System.out.println(userService.update(user2));
		
		// delete
		System.out.println(userService.delete(user2.getId()));

		
	}

}

// 字段映射错误
//Exception in thread "main" org.apache.ibatis.exceptions.PersistenceException: 
//### Error updating database.  Cause: org.apache.ibatis.executor.ExecutorException: Error selecting key or setting result to parameter object. Cause: org.apache.ibatis.reflection.ReflectionException: Could not set property 'id' of 'class com.yanxml.mybatis.train.dynamic.sql.bean.User' with value '5' Cause: java.lang.IllegalArgumentException: argument type mismatch
//### The error may exist in mybatis/UserDaoMapper.xml
//### The error may involve com.yanxml.mybatis.train.dynamic.sql.dao.UserDao.insert!selectKey
//### The error occurred while handling results
//### SQL: SELECT LAST_INSERT_ID()
//### Cause: org.apache.ibatis.executor.ExecutorException: Error selecting key or setting result to parameter object. Cause: org.apache.ibatis.reflection.ReflectionException: Could not set property 'id' of 'class com.yanxml.mybatis.train.dynamic.sql.bean.User' with value '5' Cause: java.lang.IllegalArgumentException: argument type mismatch
//	at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:26)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:154)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:141)
//	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:51)
//	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:52)
//	at com.sun.proxy.$Proxy2.insert(Unknown Source)
//	at com.yanxml.mybatis.train.dynamic.sql.service.UserService.insert(UserService.java:52)
//	at com.yanxml.mybatis.train.dynamic.sql.service.UserService.main(UserService.java:105)
//Caused by: org.apache.ibatis.executor.ExecutorException: Error selecting key or setting result to parameter object. Cause: org.apache.ibatis.reflection.ReflectionException: Could not set property 'id' of 'class com.yanxml.mybatis.train.dynamic.sql.bean.User' with value '5' Cause: java.lang.IllegalArgumentException: argument type mismatch
//	at org.apache.ibatis.executor.keygen.SelectKeyGenerator.processGeneratedKeys(SelectKeyGenerator.java:90)
//	at org.apache.ibatis.executor.keygen.SelectKeyGenerator.processAfter(SelectKeyGenerator.java:52)
//	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:48)
//	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:69)
//	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:48)
//	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:105)
//	at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:71)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:152)
//	... 6 more
//Caused by: org.apache.ibatis.reflection.ReflectionException: Could not set property 'id' of 'class com.yanxml.mybatis.train.dynamic.sql.bean.User' with value '5' Cause: java.lang.IllegalArgumentException: argument type mismatch
//	at org.apache.ibatis.reflection.wrapper.BeanWrapper.setBeanProperty(BeanWrapper.java:175)
//	at org.apache.ibatis.reflection.wrapper.BeanWrapper.set(BeanWrapper.java:57)
//	at org.apache.ibatis.reflection.MetaObject.setValue(MetaObject.java:133)
//	at org.apache.ibatis.executor.keygen.SelectKeyGenerator.setValue(SelectKeyGenerator.java:115)
//	at org.apache.ibatis.executor.keygen.SelectKeyGenerator.processGeneratedKeys(SelectKeyGenerator.java:79)
//	... 13 more
//Caused by: java.lang.IllegalArgumentException: argument type mismatch
//	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//	at java.lang.reflect.Method.invoke(Method.java:498)
//	at org.apache.ibatis.reflection.invoker.MethodInvoker.invoke(MethodInvoker.java:40)
//	at org.apache.ibatis.reflection.wrapper.BeanWrapper.setBeanProperty(BeanWrapper.java:170)
