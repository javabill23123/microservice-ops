package com.yonyou.microservice.wechat.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSourceFactory;

@Configuration
@EnableTransactionManagement
public class DatasourceConfig {
	
    @Autowired
    private Environment env;
    
    /**
     * MySQL数据源
     * @return
     * @throws Exception
     */
//    @Bean(name="dataSourceMySql")
//    @Primary
    public DataSource dataSourceMySql() throws Exception{
        Properties dataSourceProps = new Properties();
        dataSourceProps.put("driverClassName", env.getProperty("mysql.driverClassName"));
        dataSourceProps.put("url", env.getProperty("mysql.db.url"));
        dataSourceProps.put("username", env.getProperty("mysql.db.username"));
        dataSourceProps.put("password", env.getProperty("mysql.db.password"));
        dataSourceProps.put("initialSize", env.getProperty("initialSize"));
        dataSourceProps.put("maxActive", env.getProperty("maxActive"));
        dataSourceProps.put("minIdle", env.getProperty("minIdle"));
        dataSourceProps.put("maxWait", env.getProperty("maxWait"));
        dataSourceProps.put("removeAbandoned", env.getProperty("removeAbandoned"));
        dataSourceProps.put("removeAbandonedTimeout", env.getProperty("removeAbandonedTimeout"));
        dataSourceProps.put("timeBetweenEvictionRunsMillis", env.getProperty("timeBetweenEvictionRunsMillis"));
        dataSourceProps.put("minEvictableIdleTimeMillis", env.getProperty("minEvictableIdleTimeMillis"));
        dataSourceProps.put("validationQuery", env.getProperty("validationQuery"));
        dataSourceProps.put("testWhileIdle", env.getProperty("testWhileIdle"));
        dataSourceProps.put("testOnBorrow", env.getProperty("testOnBorrow"));
        dataSourceProps.put("testOnReturn", env.getProperty("testOnReturn"));
        dataSourceProps.put("poolPreparedStatements", env.getProperty("poolPreparedStatements"));
        dataSourceProps.put("maxPoolPreparedStatementPerConnectionSize",env.getProperty("maxPoolPreparedStatementPerConnectionSize"));
        dataSourceProps.put("filters", env.getProperty("filters"));
        dataSourceProps.put("connectionProperties", env.getProperty("connectionProperties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(dataSourceProps);
        return dataSource;
    }
    
    
    /**
     * MySQL 事务管理
     * @return
     * @throws Exception
     */
    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSourceMySql());
    }
    
    
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceMySql());
        bean.setTypeAliasesPackage("com.yonyou.microservice.wechat.entity");
        
        //分页插件
//        PageInterceptor pageHelper = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        properties.setProperty("dialect", "mysql");
//        pageHelper.setProperties(properties);

        //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
}
