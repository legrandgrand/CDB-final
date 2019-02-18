package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {
	
	private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private final String user = "admincdb";
	private final String password = "qwerty1234";
	
	private static final DaoFactory instance = new DaoFactory();	

	public static final DaoFactory getInstance() {
    	return instance;
    }
	
	private DaoFactory() {}
	
	
	public static CompanyDaoImp getCompanyDao(){
		return CompanyDaoImp.getInstance();
	}
	
	public static ComputerDao getComputerDao(){
		return ComputerDaoImp.getInstance();
	}
	
	/**
	 * Connect DB.
	 *
	 * @param connection the connection
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection connectDB() throws SQLException {
		   return DriverManager.getConnection( url, user, password );
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
