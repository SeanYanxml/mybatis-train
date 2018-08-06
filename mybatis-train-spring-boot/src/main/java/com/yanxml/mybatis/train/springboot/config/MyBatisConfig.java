package com.yanxml.mybatis.train.springboot.config;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageInterceptor;

@Configuration
public class MyBatisConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(value="sqlSessionFactory")
	public SqlSessionFactoryBean getSqlSessionFactory(ApplicationContext applicationContext) throws IOException{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);// 下划线 转 驼峰
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setLogImpl(Log4j2Impl.class);
		sqlSessionFactory.setConfiguration(configuration);
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/*.xml"));
		
		// 配置插件
		Properties properties = new Properties();
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("params", "pageNum=pageNum;pageSize=pageSize;");
		
		PageInterceptor pageInterceptor = new PageInterceptor();
		pageInterceptor.setProperties(properties);
		
		sqlSessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
		
		return sqlSessionFactory;
	}
	
}
