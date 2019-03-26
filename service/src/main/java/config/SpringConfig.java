package config;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan({ "dao", "controller", "mapper", "service", "validator", "view" })
@PropertySource(value = { "classpath:configuration.properties" })
public class SpringConfig {
  
  @Autowired
  private Environment env;

  /**
   * Data source.
   *
   * @return the hikari data source
   */
  @Bean
  public HikariDataSource dataSource() {

    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl(env.getRequiredProperty("URL"));
    ds.setUsername(env.getRequiredProperty("datasource.USERNAME"));
    ds.setPassword(env.getRequiredProperty("datasource.PASSWORD"));
    ds.setDriverClassName(env.getRequiredProperty("datasource.DRIVER"));
    return ds;
  }

  /**
   * Session factory.
   *
   * @return the local session factory bean
   */
  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("model");
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }
  
  /**
   * Hibernate properties.
   *
   * @return the properties
   */
  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

    return hibernateProperties;
  }

  /**
   * Hibernate transaction manager.
   *
   * @return the platform transaction manager
   */
  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }
  
  
}
