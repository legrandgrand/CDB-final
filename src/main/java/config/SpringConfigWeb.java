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
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "dao", "controller", "mapper", "service", "servlet", "validator", "view" })
@PropertySource(value = { "classpath:configuration.properties" })
public class SpringConfigWeb implements WebApplicationInitializer, WebMvcConfigurer {
  
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
    rootContext.register(SpringConfigCli.class);
    servletContext.addListener(new ContextLoaderListener(rootContext));

  }
  

  /**
   * Internal resource view resolver.
   *
   * @return the view resolver
   */
  @Bean
  public ViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setPrefix("/WEB-INF/views/");
    bean.setSuffix(".jsp");
    return bean;
  }

}
