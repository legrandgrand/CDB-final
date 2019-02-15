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

/**
 * The Class ComputerDaoImp.
 */
public class ComputerDaoImp implements ComputerDao{


	/* (non-Javadoc)
	 * @see dao.ComputerDao#listComputers()
	 */
	@Override
	public List<Computer> listComputers() {
		List<Computer> list= new ArrayList<Computer>();
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			Statement statement = connection.createStatement();
			// execute query
			ResultSet resultat = statement.executeQuery("SELECT name, introduced, discontinued, company_id FROM computer");
	
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
	public void deleteComputer(String computerName) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			String sql=("DELETE FROM computer WHERE name= ?");
			PreparedStatement statement = connection.prepareStatement(sql);
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
	public void updateComputer(Computer computer) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		
		String name=computer.getNamePC();
		int companyId=computer.getNameManuf();
		Timestamp introduced=computer.getDateB();
		Timestamp discontinued=computer.getDateF();
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			String sql=("UPDATE computer SET introduced = ?, discontinued = ?, company_id = ? WHERE name= ?");
			PreparedStatement statement = connection.prepareStatement(sql);
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
	public void addComputer(Computer computer) {
		DaoFactory factory = DaoFactory.getInstance();
		Connection connection=null;
		

		String name=computer.getNamePC();
		int companyId=computer.getNameManuf();
		Timestamp introduced=computer.getDateB();
		Timestamp discontinued=computer.getDateF();
		
		try {
			connection = factory.connectDB(connection);
			// Create objects for query 
			String sql=("INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)");
			PreparedStatement statement = connection.prepareStatement(sql);
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
