package com.swu.cjyong.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value="com.swu.cjyong.main.dao",entityManagerFactoryRef = "emf")
@EntityScan("com.swu.cjyong.main.entity")
public class DruidDataSourceConfig implements EnvironmentAware,TransactionManagementConfigurer{

    private RelaxedPropertyResolver propertyResolver;

    /**
     * 获取解析配置文件的工具类
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
    }

    /**
     * 注入阿里巴巴数据连接池
     *
     * @return
     */
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("name"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        dataSource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        dataSource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("druid.initial-size")));
        dataSource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("druid.min-idle")));
        dataSource.setMaxWait(Long.valueOf(propertyResolver.getProperty("druid.max-wait")));
        dataSource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("druid.max-active")));
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("druid.min-evictable-idle-time-millis")));
        dataSource.setPoolPreparedStatements(Boolean.valueOf(propertyResolver.getProperty("druid.poolPreparedStatements")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(propertyResolver.getProperty("druid.max-active")));
        try{
            dataSource.setFilters("stat,wall");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     *注入实体管理工厂,由Spring完全控制
     *
     * @return
     */
    @Bean(name = "emf")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());;
        entityManagerFactoryBean.setPackagesToScan("com.cjyong.spring.main");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    /**
     * 设置Druid相关属性
     *
     * @return
     */
    @Bean
    public static ServletRegistrationBean druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        //添加初始化参数: initParams
        //白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单（同时存在时，deny优于allow）如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.173");
        //登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername","cjyong");
        servletRegistrationBean.addInitParameter("loginPassword","cjyong");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","true");

        return servletRegistrationBean;
    }

    /**
     * 注入DruidStatFilter监控Web-jdbc数据
     *
     * @return
     */
    @Bean
    public static FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * 注入Jpa的事务管理器
     *
     * @return
     */
    @Override
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }
}
