package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {
	
	/** The url. */
	private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
	
	/** The user. */
	private final String user = "admincdb";
	
	/** The password. */
	private final String password = "qwerty1234";
	
	/** The Constant instance. */
	private static final DaoFactory instance = new DaoFactory();	

	public static final DaoFactory getInstance() {
    	return instance;
    }
	
	
	public static CompanyDao getCompanyDao(){
		return new CompanyDaoImp();
	}
	
	public static ComputerDao getComputerDao(){
		return new ComputerDaoImp();
	}
	
	/**
	 * Connect DB.
	 *
	 * @param connection the connection
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection connectDB(Connection connection) throws SQLException {
		   return connection = DriverManager.getConnection( url, user, password );
	}
	
	/**
	 * Close DB.
	 *
	 * @param connection the connection
	 * @throws SQLException the SQL exception
	 */
	public void closeDB(Connection connection) throws SQLException {
		connection.close();
		
	}
}
