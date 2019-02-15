package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Company;

public class CompanyDaoImp implements CompanyDao {
	private static final String SELECT = "SELECT id, name FROM company";

	//TODO Singleton
	//TODO Try with resources
	//TODO Pagination
	public CompanyDaoImp() {
		
	}
	
	/* (non-Javadoc)
	 * @see dao.CompanyDao#listCompanies()
	 */
	//TODO: stream
	@Override
	public List<Company> list() {
		List<Company> list= new ArrayList<Company>();
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			Statement statement = connection.createStatement();
			//Execute query
			ResultSet resultat = statement.executeQuery(SELECT);
	
			 while(resultat.next()) {
				 String name=resultat.getString("name");
				 Company company=new Company(name);
				 list.add(company);		 
			}
			} catch ( SQLException e ) {
				System.out.println("Couldn't connect to database");
		    } finally {
		        if ( connection != null ) {
		                try {
							connection.close();
						} catch (SQLException e) {
							System.out.println("RIP3");
							e.printStackTrace();
						}
		    }
	    }
		return list;
	}
	
	//TODO return optional
	public int getCompany(String name) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		try {
			connection = factory.connectDB(connection);
			String sql = "SELECT id FROM company WHERE name = '"+name+"'";
			// Create objects for query 
			Statement statement = connection.createStatement();
			//Execute query
			ResultSet resultat = statement.executeQuery(sql);
			if(resultat.next()) {
				return resultat.getInt("id");
			}	
			} catch ( SQLException e ) {
				System.out.println("Couldn't connect to database");
		    } finally {
		        if ( connection != null ) {
		                try {
							connection.close();
						} catch (SQLException e) {
							System.out.println("RIP3");
							e.printStackTrace();
						}
		        }
		    }
		return 0;
	}
}
