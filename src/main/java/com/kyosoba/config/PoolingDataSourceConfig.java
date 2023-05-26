package com.kyosoba.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * DBに接続するためのJava Config
 * 参考:P.104 -
 */
@Configuration
public class PoolingDataSourceConfig {
	
	// Logger
	org.slf4j.Logger logger = LoggerFactory.getLogger(PoolingDataSourceConfig.class);
	
	/**
	 * データソースW（Connectionオブジェクトのファクトリ）のBean
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("CREATE DATA SOURCE");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
		dataSource.setUrl("jdbc:postgresql://localhost/kyosoba");
		dataSource.setUsername("postgres");
		dataSource.setPassword("");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		logger.info("CREATE JdbcTemplate");
		return new JdbcTemplate(dataSource());
	}
   
	/* 
	 *
	   	このBean定義について@Serviceを付けているので既にあるといったエラー。ちなみにメソッド名の先頭が大文字だと動いたけどどういう事？
	   	しかも、DAOはメソッド名が先頭でも、インジェクションするときに２つ見つかったというエラーが出た（メソッド名の先頭が大文字で。）
	   	DAOのメソッド名を小文字にしたら、Serviceのエラーと一緒。
	   	
	   	@Bean 
		public KyosobaInfoService kyosobaInfoService() {
			logger.info("CREATE KyosobaInfoService");
			return new KyosobaInfoService();
		}
		***************************
		APPLICATION FAILED TO START
		***************************
		
		Description:
		
		The bean 'kyosobaInfoService', defined in class path resource [com/kyosoba/config/PoolingDataSourceConfig.class],
		 could not be registered. 
		 A bean with that name has already been defined in file 
		 [C:\Users\f\Desktop\2_ITのおべんきょう\2023-05-14.競走馬アプリを作る\
		 Spring\detail-api\target\classes\com\kyosoba\service\KyosobaInfoService.class] 
		 and overriding is disabled.
		 
		 
		 ↓DAOの方
		***************************
		APPLICATION FAILED TO START
		***************************
		
		Description:
		
		Field dao in com.kyosoba.service.KyosobaInfoService required a single bean, but 2 were found:
		        - jdbcKyosobaDao: defined in file [C:\Users\f\Desktop\2_ITのおべんきょう\2023-05-14.競走馬アプリを作る\Spring\detail-api\target\classes\com\kyosoba\dao\JdbcKyosobaDao.class]
		        - JdbcKyosobaDao: defined by method 'JdbcKyosobaDao' in class path resource [com/kyosoba/config/PoolingDataSourceConfig.class]
	 */
	
	
	
}
