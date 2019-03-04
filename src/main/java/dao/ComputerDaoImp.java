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

import model.Company;
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
      "SELECT id, name, introduced, discontinued, company_id FROM computer ";
  private static final String SELECT_ONE = 
      "SELECT id, name, introduced, discontinued, company_id FROM computer ";
  private static final String PAGING = "LIMIT 20 OFFSET ";
  private static final String DELETE = "DELETE FROM computer WHERE name= ?";

  private static final ComputerDaoImp instance = new ComputerDaoImp();

  private static final Logger logger = LoggerFactory.getLogger(ComputerDaoImp.class);

  private CompanyDaoImp companyDao = CompanyDaoImp.getInstance();
  private DaoFactory factory = DaoFactory.getInstance();

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
    List<Company> companyList = new ArrayList<Company>();
      companyList = companyDao.list();
    
    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement.executeQuery(SELECT);
      while (resultat.next()) {
        Computer computer = new Computer();
        computer.setName(resultat.getString("name"));
        computer.setDateIntro(resultat.getTimestamp("introduced"));
        computer.setDateDiscontinuation(resultat.getTimestamp("discontinued"));
        computer.setId(resultat.getInt("id"));
        
        Company company = new Company();
        company.setId(resultat.getInt("company_id"));

        if (company.getId() != 0 && company.getId()<companyList.size()+1) {

          company.setName(companyList.get(company.getId()-1).getName());

        }
        computer.setCompany(company);
        list.add(computer);
      }
      
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Size of list: " + list.size());
    return list;

  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.ComputerDao#listComputers()
   */
  @Override
  // TODO: stream
  public List<Computer> listPage(int page) {
    List<Computer> list = new ArrayList<Computer>();
    List<Company> companyList = new ArrayList<Company>();
    companyList = companyDao.list();


    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement.executeQuery(SELECT + PAGING + page);
      while (resultat.next()) {
        Computer computer = new Computer();
        computer.setName(resultat.getString("name"));
        computer.setDateIntro(resultat.getTimestamp("introduced"));
        computer.setDateDiscontinuation(resultat.getTimestamp("discontinued"));
        computer.setId(resultat.getInt("id"));
        Company company = new Company();
        company.setId(resultat.getInt("company_id"));
        if (company.getId() != 0 && company.getId()<companyList.size()+1) {

          company.setName(companyList.get(company.getId()-1).getName());

        }
        computer.setCompany(company);
        list.add(computer);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Size of list: " + list.size());
    return list;

  }

  @Override
  public List<Computer> getComputer(Computer computer) {
    List<Computer> list = new ArrayList<Computer>();

    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement.executeQuery(SELECT_ONE + "WHERE id=" + computer.getId());
      while (resultat.next()) {
        Computer computer2 = new Computer();
        computer2.setName(resultat.getString("name"));
        computer2.setDateIntro(resultat.getTimestamp("introduced"));
        computer2.setDateDiscontinuation(resultat.getTimestamp("discontinued"));
        computer2.setId(resultat.getInt("id"));
        
        Company company = new Company();
        company.setId(resultat.getInt("company_id"));
        if (company.getId() != 0) {
          computer2.setCompany(companyDao.getCompanyFromId(company).get(0));
        }
        computer2.setCompany(company);
        list.add(computer2);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Returning computer: " + computer);
    return list;

  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    List<Computer> list = new ArrayList<Computer>();

    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement
          .executeQuery(SELECT_ONE + "WHERE name LIKE '%" + computer.getName() + "%'");
      while (resultat.next()) {
        Computer computer2 = new Computer();
        computer2.setName(resultat.getString("name"));
        computer2.setDateIntro(resultat.getTimestamp("introduced"));
        computer2.setDateDiscontinuation(resultat.getTimestamp("discontinued"));
        computer2.setId(resultat.getInt("id"));
        
        Company company = new Company();
        company.setId(resultat.getInt("company_id"));
        if (company.getId() != 0) {
          computer2.setCompany(companyDao.getCompanyFromId(company).get(0));
        }
        computer2.setCompany(company);
        list.add(computer2);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Returning computer: " + computer);
    return list;

  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.ComputerDao#deleteComputer(java.lang.String)
   */
  @Override
  public void delete(Computer computer) {

    try (Connection connection = factory.connectDb();
        PreparedStatement statement = connection.prepareStatement(DELETE)) {
      statement.setString(1, computer.getName());
      statement.executeUpdate();
      logger.debug("Deleted computer:" + computer.getName());
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

    String name = computer.getName();
    int companyId = computer.getCompany().getId();
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

    String name = computer.getName();
    int companyId = computer.getCompany().getId();
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
