package dao;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
  
  private static final Logger logger = LoggerFactory.getLogger(Config.class);
  private Properties configFile;

  public Config() {
    configFile = new Properties();
    try {
      configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }

  public String getProperty(String key) {
    String value = this.configFile.getProperty(key);
    return value;
  }
}