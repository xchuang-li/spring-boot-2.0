package com.cay.sbt.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 主数据源配置类
 * */
@Configuration
@MapperScan(basePackages = "com.cay.sbt.mapper.master",sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterDataSourceConfig {

    /**
     * 创建数据源
     *
     * */
    @Bean("masterDataSource")
    //引用application.yml中的配置
    @ConfigurationProperties(prefix = "spring.datasource.master")
    //有多个实现时，spring无法通过类型区分应用哪个，此注解代表 优先默认选择，此处，定义主数据源
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建工厂
     * */
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource,@Qualifier("masterConfiguration") org.apache.ibatis.session.Configuration configuration) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(configuration);
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建事务
     * */
    @Bean("masterDataSourceTransactionManager")
    public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("masterDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     * */
    @Bean("masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 使用多数据源时，导致单数据源中的配置，如驼峰命名开启  失效，因为不知道这个配置具体要使用
     * 在哪个数据源上，所以，在主从数据源配置类中都引入配置，并在SqlSessionFactory中注入配置
     * */
    @Bean("masterConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration.master")
    public org.apache.ibatis.session.Configuration masterConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }
}
