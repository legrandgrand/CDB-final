package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoFactory {

  private Config config = new Config();

  private final String url = config.getProperty("url");
  private final String user = config.getProperty("user");
  private final String password = config.getProperty("password");
  
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
    return DriverManager.getConnection(url, user, password);
  }
}
