package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

  private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
  private final String user = "admincdb";
  private final String password = "qwerty1234";

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

  public static CompanyDaoImp getCompanyDao() {
    return CompanyDaoImp.getInstance();
  }

  public static ComputerDao getComputerDao() {
    return ComputerDaoImp.getInstance();
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
