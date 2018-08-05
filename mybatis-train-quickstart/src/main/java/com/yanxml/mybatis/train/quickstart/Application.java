package com.yanxml.mybatis.train.quickstart;

import java.io.IOException;
import com.yanxml.mybatis.train.quickstart.bean.User;
import com.yanxml.mybatis.train.quickstart.service.UserService;

//import java.io.InputStream;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Application {
	
	public static void main(String []args) throws IOException{
/*		String DBConfigUrl="config/DBConfig.xml";
		UserService userService=new UserServiceImpl();
		//UserServiceImpl 因为没有使用Spring 所以这些东西都需要手动进行加载
		InputStream is=Application.class.getClassLoader().getResourceAsStream(DBConfigUrl);
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session=sessionFactory.openSession();*/
		//User user=session.selectOne(arg0, arg1);
		
		// Test New API
		UserService userService=new UserService();
//		
//		User user=userService.getById(1);
//		
//		System.out.println(user);
		
		// Test Old API
				
		User oldUser=userService.getByIdOld(1);
		
		System.out.println(oldUser);
	}
}


/**
 *Exception in thread "main" org.apache.ibatis.exceptions.PersistenceException: 
### Error opening session.  Cause: java.lang.NullPointerException
### Cause: java.lang.NullPointerException
	at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:26)
	at org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSessionFromDataSource(DefaultSqlSessionFactory.java:91)
	at org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSession(DefaultSqlSessionFactory.java:46)
	at com.us.demo.Application.main(Application.java:22)
Caused by: java.lang.NullPointerException
	at org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSessionFromDataSource(DefaultSqlSessionFactory.java:86)
	... 2 more
session 报空指针
原因是因为 配置文件 配置错误
 *
 */
/**
 *Exception in thread "main" java.lang.NullPointerException
	at com.us.demo.service.impl.UserServiceImpl.getOneUser(UserServiceImpl.java:11)
	at com.us.demo.Application.main(Application.java:25)
	因为Dao层没有自动进行加载 每次需要从SqlSession的 session 加载对应的映射Dao层类
 */
/**
 * Exception in thread "main" org.apache.ibatis.binding.BindingException: Type interface com.us.demo.dao.UserDao is not known to the MapperRegistry.
	at org.apache.ibatis.binding.MapperRegistry.getMapper(MapperRegistry.java:47)
	at org.apache.ibatis.session.Configuration.getMapper(Configuration.java:655)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.getMapper(DefaultSqlSession.java:222)
	at com.us.demo.service.impl.UserServiceImpl.<init>(UserServiceImpl.java:19)
	at com.us.demo.Application.main(Application.java:26)
	DaoMapper的配置错误 导致文件找不到那个东西
 */

/**
 * Thu Aug 18 15:55:31 GMT+08:00 2016 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
   com.us.demo.model.User@5cc7c2a6
   BUG解决了 输出OK
 * */
 