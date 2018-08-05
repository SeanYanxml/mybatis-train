package com.yanxml.mybatis.train.quickstart.service;

import org.apache.ibatis.session.SqlSession;

import com.yanxml.mybatis.train.quickstart.bean.User;
import com.yanxml.mybatis.train.quickstart.dao.UserDao;
import com.yanxml.mybatis.train.quickstart.util.SessionUtil;

public class UserService{
		
	// 使用Mybatis新API
	public User getById(Integer id) {
		User resultUser = null;
		SqlSession session = SessionUtil.getSession();
		try{
			// 注意 这里需要通过 相同的映射名称进行映射(即UserDaoMapper.xml文件的namespace名称与 UserDao.class的 namespace一致)
			UserDao userDao = session.getMapper(UserDao.class);
			resultUser =userDao.getById(id);
		}finally{
			session.close();
		}
		return resultUser;
	}
	
	// 使用Mybatis旧APi
	public User getByIdOld(Integer id){
		User resultUser = null;
		SqlSession session = SessionUtil.getSession();
		try{
			resultUser = (User)session.selectOne("com.yanxml.mybatis.train.quickstart.dao.OldUserDao.getById",1);
		}finally{
			session.close();
		}
		return resultUser;
	}
	
}

// https://blog.csdn.net/wjb_2016/article/details/51768205

//Exception in thread "main" org.apache.ibatis.exceptions.PersistenceException: 
//### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.yanxml.mybatis.train.quickstart.dao.OldUserDao.getById
//### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.yanxml.mybatis.train.quickstart.dao.OldUserDao.getById
//	at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:26)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:111)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:102)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(DefaultSqlSession.java:66)
//	at com.yanxml.mybatis.train.quickstart.service.UserService.getByIdOld(UserService.java:30)
//	at com.yanxml.mybatis.train.quickstart.Application.main(Application.java:33)
//Caused by: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.yanxml.mybatis.train.quickstart.dao.OldUserDao.getById
//	at org.apache.ibatis.session.Configuration$StrictMap.get(Configuration.java:797)
//	at org.apache.ibatis.session.Configuration.getMappedStatement(Configuration.java:631)
//	at org.apache.ibatis.session.Configuration.getMappedStatement(Configuration.java:624)
//	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:107)
//	... 4 more
//大家在Mybatis使用过程中，尤其是初次接触使用会出现“Mapped Statements collection does not contain value for（后面是什么类什么方法之类的）“错误提示
//常见的错误原因有几种：
//1、mapper.xml中没有加入namespace
//2、mapper.xml中的方法和接口mapper的方法不对应
//3、mapper.xml没有加入到mybatis-config.xml中(即总的配置文件)，例外：配置了mapper文件的包路径的除外
//4、mapper.xml文件名和所写的mapper名称不相同。

// 两种写法各有好处
// 旧写法 不用创建 UserDao.java 文件
// 新写法 映射方便 一次定义可以多次使用。写法更加简单，不容易出错。
