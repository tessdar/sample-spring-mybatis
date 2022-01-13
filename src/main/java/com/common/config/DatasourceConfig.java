//package com.common.config;
//
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jndi.JndiObjectFactoryBean;
//
//@Configuration
//public class DatasourceConfig {
//
//	@Bean(name = "dataSource")
//	public DataSource dataSource() throws NamingException {
//	    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//	    jndiObjectFactoryBean.setJndiName("java:/jdbc/sampleDS" );
//	    jndiObjectFactoryBean.setProxyInterface(DataSource.class);
//	    jndiObjectFactoryBean.setLookupOnStartup(false);
//	    jndiObjectFactoryBean.afterPropertiesSet();
//	    return (DataSource) jndiObjectFactoryBean.getObject();
//	}
//	
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
//    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//    	sqlSessionFactoryBean.setDataSource(dataSource);
//    	sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/sql/mybatis-config.xml"));
//    	sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql/sample/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//	
//    @Bean(name = "sqlSession")
//    public SqlSessionTemplate sqlSession(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
