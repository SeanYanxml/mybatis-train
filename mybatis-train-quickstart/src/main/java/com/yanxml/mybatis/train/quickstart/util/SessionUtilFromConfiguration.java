package com.yanxml.mybatis.train.quickstart.util;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.yanxml.mybatis.train.quickstart.dao.UserDao;

/**
 * 从Configuration类中加载配置。
 */
public class SessionUtilFromConfiguration {
	class DataSourceConst {
		public final static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
		public final static String JDBC_URL = "jdbc:mysql://localhost:3306/school";
		public final static String JDBC_USERNAME = "root";
		public final static String JDBC_PASSWORD = "admin";

	}
	private SqlSessionFactory sqlSessionFactory = null;
	static {
		// dataSource
		DruidDataSource dataSource = null;
		dataSource = new DruidDataSource();
		dataSource.setDriverClassName(DataSourceConst.JDBC_DRIVER_CLASS);
		dataSource.setUrl(DataSourceConst.JDBC_URL);
		dataSource.setUsername(DataSourceConst.JDBC_USERNAME);
		dataSource.setPassword(DataSourceConst.JDBC_PASSWORD);
		// other config
		dataSource.setMaxActive(30);
		dataSource.setInitialSize(30);
		// dataSource.setMaxIdle(1000);
		dataSource.setBreakAfterAcquireFailure(true);

		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development",
				transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		// configuration.addMapper(class);
		configuration.addMapper(UserDao.class);
		// configuration.addMapper(className);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(configuration);
	}

	public SqlSession getSession(){
		return sqlSessionFactory.openSession();
	}
}
