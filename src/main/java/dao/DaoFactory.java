package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DaoFactory {

  private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
  private final String user = "admincdb";
  private final String password = "qwerty1234";

  private static final HikariConfig hikariConfig = new HikariConfig("/config.properties");
  HikariDataSource ds = new HikariDataSource(hikariConfig);
  private static Connection connection;

  private static final DaoFactory instance = new DaoFactory();

  private static final Logger logger = LoggerFactory.getLogger(ComputerDaoImp.class);

  /**
   * Get instance of a new dao factory.
   */
  public static final DaoFactory getInstance() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      logger.error(e.getMessage(), e);
    }
    return instance;
  }

  private DaoFactory() {
  }

  /**
   * Connect DB.
   *
   * @return the connection
   * @throws SQLException the SQL exception
   */
  public Connection connectDb() throws SQLException {
    // return DriverManager.getConnection(url, user, password);
    if (connection == null || connection.isClosed()) {
      connection = ds.getConnection();
    }
    return connection;
  }
}
