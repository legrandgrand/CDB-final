package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringConfig {
  private static final HikariConfig hikariConfig = new HikariConfig("/config.properties");
  HikariDataSource ds = new HikariDataSource(hikariConfig);
  
  private static Connection connection;
  
  /**
   * Connect DB.
   *
   * @return the connection
   * @throws SQLException the SQL exception
   */
  public Connection connectDb() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = ds.getConnection();
    }
    return connection;
  }
  
}
