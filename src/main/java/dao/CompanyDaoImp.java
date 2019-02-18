package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Company;

public class CompanyDaoImp implements CompanyDao {

	private static final String SELECT_ID = "SELECT id FROM company WHERE name = ?";

	private static final String SELECT = "SELECT id, name FROM company";
	
	//TODO Pagination
	//TODO: stream
	private static final CompanyDaoImp instance = new CompanyDaoImp();	

	public static final CompanyDaoImp getInstance() {
    	return instance;
    }
	
	private CompanyDaoImp() {}
	
	
	/* (non-Javadoc)
	 * @see dao.CompanyDao#listCompanies()
	 */
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
	
	public Optional<Integer> getCompany(String name) {
		DaoFactory factory = DaoFactory.getInstance();
		Optional<Integer> companyId=null;
		try (Connection connection = factory.connectDB();
			PreparedStatement statement = connection.prepareStatement(SELECT_ID)){
			statement.setString(1, "name");
			ResultSet resultat = statement.executeQuery(SELECT_ID);
			if(resultat.next()) {
				companyId = Optional.ofNullable(resultat.getInt("id"));
			}
		} catch ( SQLException e ) {
			System.out.println("Couldn't connect to database");
		}
		return companyId;
	}
}
