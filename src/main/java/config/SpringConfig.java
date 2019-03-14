package config;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan({ "dao", "controller", "mapper", "service", "validator", "view" })
@PropertySource(value = { "classpath:configuration.properties" })
public class SpringConfigCli {

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
}
