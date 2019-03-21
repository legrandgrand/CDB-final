package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"validator"})
@PropertySource(value = { "classpath:configuration.properties" })
public class SpringConfigTest {}
