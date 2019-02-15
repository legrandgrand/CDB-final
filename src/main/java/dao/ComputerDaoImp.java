package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Computer;

//TODO Try with resources
//TODO Singleton
//TODO Pagination
public class ComputerDaoImp implements ComputerDao{


	private static final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE computer SET introduced = ?, discontinued = ?, company_id = ? WHERE name= ?";
	private static final String SELECT= "SELECT name, introduced, discontinued, company_id FROM computer";
	private static final String DELETE= "DELETE FROM computer WHERE name= ?";



	/* (non-Javadoc)
	 * @see dao.ComputerDao#listComputers()
	 */
	@Override
	//TODO: stream
	public List<Computer> list() {
		List<Computer> list= new ArrayList<Computer>();
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			Statement statement = connection.createStatement();
			// execute query
			ResultSet resultat = statement.executeQuery(SELECT);
	
			 while(resultat.next()) {
				 String name=resultat.getString("name");
				 int companyId=resultat.getInt("company_id");
				 Timestamp introduced=resultat.getTimestamp("introduced");
				 Timestamp discontinued=resultat.getTimestamp("discontinued");
				 Computer computer=new Computer(name, companyId, introduced, discontinued);
				 list.add(computer);
			}
			} catch ( SQLException e ) {
				System.out.println("Couldn't connect to database");
		    } finally {
		        if ( connection != null ) {
		                try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    }
	    }
		return list;
		
	}


	/* (non-Javadoc)
	 * @see dao.ComputerDao#deleteComputer(java.lang.String)
	 */
	@Override
	public void delete(String computerName) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setString(1, computerName);
			// execute query
			statement.executeUpdate();	
			} catch ( SQLException e ) {
				System.out.println("Couldn't connect to database");
		    } finally {
		        if ( connection != null ) {
		                try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    }
	    }
		
	}

	
	
	/* (non-Javadoc)
	 * @see dao.ComputerDao#updateComputer(model.Computer)
	 */
	@Override
	public void update(Computer computer) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		
		String name=computer.getNamePC();
		int companyId=computer.getNameManufacturer();
		Timestamp introduced=computer.getDateIntro();
		Timestamp discontinued=computer.getDateDiscontinuation();
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			statement.setTimestamp(1, introduced);
			statement.setTimestamp(2, discontinued);
			statement.setInt(3, companyId);
			statement.setString(4, name);
			// execute query
			statement.executeUpdate();
			} catch ( SQLException e ) {
				System.out.println("Couldn't connect to database");
		    } finally {
		        if ( connection != null ) {
		                try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    }
	    }
	}

	
	
	/* (non-Javadoc)
	 * @see dao.ComputerDao#addComputer(model.Computer)
	 */
	@Override
	public void add(Computer computer) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		

		String name=computer.getNamePC();
		int companyId=computer.getNameManufacturer();
		Timestamp introduced=computer.getDateIntro();
		Timestamp discontinued=computer.getDateDiscontinuation();
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, name);
			statement.setTimestamp(2, introduced);
			statement.setTimestamp(3, discontinued);
			statement.setInt(4, companyId);
			// execute query
			statement.executeUpdate();
			System.out.println("yay");
			} catch ( SQLException e ) {
				System.out.println("Couldn't connect to database");
		    } finally {
		        if ( connection != null ) {
		                try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		        }
		    }
	}
	
	
}
