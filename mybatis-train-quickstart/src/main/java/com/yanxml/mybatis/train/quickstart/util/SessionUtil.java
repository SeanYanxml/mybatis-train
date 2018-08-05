package com.yanxml.mybatis.train.quickstart.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * 从XML配置文件内加载SqlSessionFactory配置。
 * */
public class SessionUtil {
	
	private static SqlSessionFactory sqlSessionFactory = null;
	
	static {
		// load xml config file.
		String dbConfigUrl = "config/DBConfig.xml";
		try {
			InputStream is = Resources.getResourceAsStream(dbConfigUrl);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is); 
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static SqlSession getSession(){
		return sqlSessionFactory.openSession();
	}

}

// history write.
//public static SqlSession getSession() throws IOException{
//String DBConfigUrl="config/DBConfig.xml";
////UserServiceImpl 因为没有使用Spring 所以这些东西都需要手动进行加载
////InputStream is=SessionUtil.class.getClassLoader().getResourceAsStream(DBConfigUrl);
////SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(DBConfig));
////SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(is);
//
//SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(DBConfigUrl));
//SqlSession session=sessionFactory.openSession();
//return session;
//}
