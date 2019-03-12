package config;

import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan({ "dao", "controller", "mapper", "service", "servlet", "validator", "view" })
@PropertySource(value = { "classpath:configuration.properties" })
public class SpringConfig implements WebApplicationInitializer {

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

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(SpringConfig.class);
    servletContext.addListener(new ContextLoaderListener(rootContext));

  }
}
