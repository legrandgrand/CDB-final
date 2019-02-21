package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO Pagination
public class ComputerDaoImp implements ComputerDao {

  private static final String INSERT = 
      "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
  private static final String UPDATE = 
      "UPDATE computer SET introduced = ?, discontinued = ?, company_id = ? WHERE name= ?";
  private static final String SELECT = 
      "SELECT name, introduced, discontinued, company_id FROM computer"; 
  private static final String SELECT_ONE = 
      "SELECT name, introduced, discontinued, company_id FROM computer"; 
  private static final String DELETE = 
      "DELETE FROM computer WHERE name= ?";

  private static final ComputerDaoImp instance = new ComputerDaoImp();
  
  private static final Logger logger = LoggerFactory.getLogger(ComputerDaoImp.class);

  /**
   * Instantiates a new computer dao imp.
   */
  private ComputerDaoImp() {
  }

  public static final ComputerDaoImp getInstance() {
    return instance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.ComputerDao#listComputers()
   */
  @Override
  // TODO: stream
  public List<Computer> list() {
    List<Computer> list = new ArrayList<Computer>();
    DaoFactory factory = DaoFactory.getInstance();

    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement.executeQuery(SELECT);
      while (resultat.next()) {
        String name = resultat.getString("name");
        int companyId = resultat.getInt("company_id");
        Timestamp introduced = resultat.getTimestamp("introduced");
        Timestamp discontinued = resultat.getTimestamp("discontinued");
        Computer computer = new Computer(name, companyId, introduced, discontinued);
        list.add(computer);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Size of list: " +  list.size());
    return list;

  }
  
  public Computer getComputer(String name) {
    DaoFactory factory = DaoFactory.getInstance();
    Computer computer =  new Computer ("", 1 , null, null);

    try (Connection connection = factory.connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
      statement.setString(1, "name");
      ResultSet resultat = statement.executeQuery(SELECT_ONE);
      if (resultat.next()) {
        name = resultat.getString("name");
        int companyId = resultat.getInt("company_id");
        Timestamp introduced = resultat.getTimestamp("introduced");
        Timestamp discontinued = resultat.getTimestamp("discontinued");
        computer = new Computer(name, companyId, introduced, discontinued);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Returning computer: "+computer);
    return computer;

  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.ComputerDao#deleteComputer(java.lang.String)
   */
  @Override
  public void delete(String computerName) {
    DaoFactory factory = DaoFactory.getInstance();

    try (Connection connection = factory.connectDb();
        PreparedStatement statement = connection.prepareStatement(DELETE)) {
      statement.setString(1, computerName);
      statement.executeUpdate();
      logger.debug("Deleted computer:" + computerName);
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.ComputerDao#updateComputer(model.Computer)
   */
  @Override
  public void update(Computer computer) {
    DaoFactory factory = DaoFactory.getInstance();

    String name = computer.getName();
    int companyId = computer.getNameManufacturer();
    Date date1 = computer.getDateIntro();
    Date date2 = computer.getDateDiscontinuation();

    Timestamp introduced = toTimestamp(date1);
    Timestamp discontinued = toTimestamp(date2);

    try (Connection connection = factory.connectDb();
        PreparedStatement statement = connection.prepareStatement(UPDATE)) {
      statement.setTimestamp(1, introduced);
      statement.setTimestamp(2, discontinued);
      statement.setInt(3, companyId);
      statement.setString(4, name);
      statement.executeUpdate();
      logger.debug("Updated computer:" + computer);
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.ComputerDao#addComputer(model.Computer)
   */
  @Override
  public void add(Computer computer) {
    DaoFactory factory = DaoFactory.getInstance();

    String name = computer.getName();
    int companyId = computer.getNameManufacturer();
    Date date1 = computer.getDateIntro();
    Date date2 = computer.getDateDiscontinuation();

    Timestamp introduced = toTimestamp(date1);
    Timestamp discontinued = toTimestamp(date2);

    try (Connection connection = factory.connectDb();
        PreparedStatement statement = connection.prepareStatement(INSERT)) {
      statement.setString(1, name);
      statement.setTimestamp(2, introduced);
      statement.setTimestamp(3, discontinued);
      statement.setInt(4, companyId);

      statement.executeUpdate();
      logger.debug("Added computer:" + computer);
    } catch (SQLException e) {

      logger.error(e.getMessage(), e);
    }
  }

  /**
   * Changes a date to a timestamp.
   *
   * @param date the date
   * @return the timestamp
   */
  public Timestamp toTimestamp(Date date) {
    if (null != date) {
      Timestamp ts = new Timestamp(date.getTime());
      return ts;
    } else {
      return null;
    }

  }

}
