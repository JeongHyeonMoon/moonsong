package com.nts.moonsong;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.nts.**.repository")
@PropertySource("classpath:mysql.properties")
public class PersistenceConfiguration {
	@Autowired
	Environment enviroment;

	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(enviroment.getProperty("jdbc.driver-class-name"));
		dataSourceBuilder.url(enviroment.getProperty("jdbc.url"));
		dataSourceBuilder.username(enviroment.getProperty("jdbc.username"));
		dataSourceBuilder.password(enviroment.getProperty("jdbc.password"));
		return dataSourceBuilder.build();
	}

	@Bean
	public DataSourceTransactionManager mybatisTransaction(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		// mybatis mapper 위치 설정
		sessionFactory.setMapperLocations(
			new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		return sessionFactory;
	}
}
