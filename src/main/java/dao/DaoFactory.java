package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

  Config config = new Config();

  String url = config.getProperty("url");
  String user = config.getProperty("user");
  String password = config.getProperty("password");
  
  private static final DaoFactory instance = new DaoFactory();
  
  /**
   * Get instance of a new dao factory.
   */
  public static final DaoFactory getInstance() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
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
