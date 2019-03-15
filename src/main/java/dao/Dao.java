package dao;

import com.zaxxer.hikari.HikariDataSource;

import mapper.RowMapperComputer;
import model.Computer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Dao {
  
  private Connection connection = null;
  
  @Autowired
  private HikariDataSource ds;
  
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
  
  public HikariDataSource getDataSource() {
    return ds;
  }
  
  
  

}
