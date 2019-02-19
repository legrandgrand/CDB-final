package dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class DaoFactoryTest {

  @InjectMocks
  private DaoFactory daoFactory;
  @Mock
  private Connection mockConnection;
  @Mock
  private Statement mockStatement;
  private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
  private final String user = "admincdb";
  private final String password = "qwerty1234";

  @Before
  public void setUp() throws SQLException {
    MockitoAnnotations.initMocks(this);
    mockConnection = DriverManager.getConnection(url, user, password);
  }

  @Test
  public void testConnectDb() throws SQLException {
    assertEquals(mockConnection, daoFactory.connectDb());
    // Case 1: can't connect to DB
    // Case 2:

  }

}
