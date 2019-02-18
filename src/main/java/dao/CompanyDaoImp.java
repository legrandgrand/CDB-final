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
	
	//TODO Pagination

	private static final CompanyDaoImp instance = new CompanyDaoImp();	

	public static final CompanyDaoImp getInstance() {
    	return instance;
    }
	
	private CompanyDaoImp() {}
	
	
	/* (non-Javadoc)
	 * @see dao.CompanyDao#listCompanies()
	 */
	//TODO: stream
	@Override
	public List<Company> list() {
		List<Company> list= new ArrayList<Company>();
		DaoFactory factory = DaoFactory.getInstance();
		try (Connection connection = factory.connectDB();
			 Statement statement = connection.createStatement()){
			
			try (ResultSet resultat = statement.executeQuery(SELECT)){
				
				while(resultat.next()) {
				 String name=resultat.getString("name");
				 Company company=new Company(name);
				 list.add(company);		 
				}
			}
			} catch ( SQLException e ) {
				System.out.println("An error happened during the query");
		    } 
		return list;
	}
	
	//TODO return optional
	public int getCompany(String name) {
		DaoFactory factory = DaoFactory.getInstance();
		String sql = "SELECT id FROM company WHERE name = '"+name+"'";
		try (Connection connection = factory.connectDB();
			 Statement statement = connection.createStatement()){
		
			try (ResultSet resultat = statement.executeQuery(sql)){
				if(resultat.next()) {
				return resultat.getInt("id");
				}	
			}
			
		} catch ( SQLException e ) {
			System.out.println("Couldn't connect to database");
		} 
		return 0;
	}
}
