package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
  
  private static final HikariConfig hikariConfig = new HikariConfig("/config.properties");
  HikariDataSource ds = new HikariDataSource(hikariConfig);
  private static Connection connection;

  private static final Database instance = new Database();

  private static final Logger logger = LoggerFactory.getLogger(ComputerDaoImp.class);

  /**
   * Get instance of a new dao factory.
   */
  public static final Database getInstance() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      logger.error(e.getMessage(), e);
    }
    return instance;
  }

  private Database() {
  }

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
