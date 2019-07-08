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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
@Configuration
@MapperScan(basePackages = "com.cay.sbt.mapper.cluster",sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class ClusterDataSourceConfig {
    /**
     * 创建数据源
     *
     * */
    @Bean("clusterDataSource")
    //引用application.yml中的配置
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DataSource clusterDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建工厂
     * */
    @Bean("clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource,@Qualifier("clusterConfiguration") org.apache.ibatis.session.Configuration configuration) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(configuration);
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建事务
     * */
    @Bean("clusterDataSourceTransactionManager")
    public DataSourceTransactionManager clusterDataSourceTransactionManager(@Qualifier("clusterDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     * */
    @Bean("clusterSqlSessionTemplate")
    public SqlSessionTemplate clusterSqlSessionTemplate(@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("clusterConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration.cluster")
    public org.apache.ibatis.session.Configuration clusterConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }
}
