package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TODO: stream
@Repository
public class ComputerDaoImp extends Dao implements ComputerDao {

  private static final String INSERT = "INSERT INTO computer "
      + "(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
  private static final String UPDATE = "UPDATE computer "
      + "SET introduced = ?, discontinued = ?, company_id = ? WHERE name= ?";
  private static final String SELECT = "SELECT id, name, introduced, discontinued, company_id "
      + "FROM computer ";
  private static final String SELECT_ID = SELECT + "WHERE id=?";
  private static final String SELECT_ORDER_BY = SELECT + " ORDER BY ISNULL(";
  private static final String PAGE = "LIMIT ? OFFSET ? ";
  private static final String GET_MAX_ID = "SELECT MAX(id) FROM computer";
  private static final String DELETE = "DELETE FROM computer WHERE name= ?";

  private static final Logger logger = LoggerFactory.getLogger(ComputerDaoImp.class);
  
  @Autowired
  private CompanyDaoImp companyDao; 
  
  private ComputerDaoImp() {}

  @Override
  public List<Computer> list() {
    List<Computer> list = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();
    companyList = companyDao.list();

    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet resultat = statement.executeQuery()) {

      while (resultat.next()) {
        list.add(setComputerData(resultat, companyList));
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Size of list: " + list.size());
    return list;

  }

  @Override
  public List<Computer> orderBy(String column, String type, int limit, int offset) {
    List<Computer> list = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();
    companyList = companyDao.list();

    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY 
            + column + ") , " + column + " " + type + " LIMIT " + limit + " OFFSET " + offset);
        ResultSet resultat = statement.executeQuery()) {

      while (resultat.next()) {
        list.add(setComputerData(resultat, companyList));
      }

    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Size of list: " + list.size());
    return list;
  }

  @Override
  public List<Computer> listPage(int limit, int page) {
    List<Computer> list = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();
    companyList = companyDao.list();

    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT + PAGE)) {
      statement.setInt(1, limit);
      statement.setInt(2, page);    
      ResultSet resultat = statement.executeQuery();
      while (resultat.next()) {
        list.add(setComputerData(resultat, companyList));
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Size of list: " + list.size() + "offset: " + page);
    return list;

  }

  // TODO: Upgrade the company lookup to only lookup for 1 company
  @Override
  public List<Computer> getComputer(Computer computer) {
    List<Computer> list = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();
    companyList = companyDao.list();

    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT_ID)) {
      statement.setInt(1, computer.getId());
      ResultSet resultat = statement.executeQuery();
      while (resultat.next()) {
        list.add(setComputerData(resultat, companyList));
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Returning computer: " + computer);
    return list;
  }

  @Override
  public int getMaxId() {
    int id = 0;
    
    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(GET_MAX_ID);
            ResultSet resultat = statement.executeQuery()) {
      
      while (resultat.next()) {
        id = resultat.getInt("MAX(id)");
      }
      
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    
    logger.debug("Returning max id: " + id);
    return id;
  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    List<Computer> list = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();
    companyList = companyDao.list();

    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT
            + "WHERE name LIKE '%" + computer.getName() + "%'");
        ResultSet resultat = statement.executeQuery()) {

      while (resultat.next()) {
        list.add(setComputerData(resultat, companyList));
      }
      
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Returning computer: " + computer);
    return list;
  }

  @Override
  public void delete(Computer computer) {

    try (Connection connection = connectDb();
        PreparedStatement statement = connection.prepareStatement(DELETE)) {
      statement.setString(1, computer.getName());
      statement.executeUpdate();
      logger.debug("Deleted computer:" + computer.getName());
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

  }

  @Override
  public void update(Computer computer) {

    String name = computer.getName();
    int companyId = computer.getCompany().getId();
    Date date1 = computer.getDateIntro();
    Date date2 = computer.getDateDiscontinuation();

    Timestamp introduced = toTimestamp(date1);
    Timestamp discontinued = toTimestamp(date2);

    try (Connection connection = connectDb();
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

  @Override
  public void add(Computer computer) {

    String name = computer.getName();
    int companyId = computer.getCompany().getId();
    Date date1 = computer.getDateIntro();
    Date date2 = computer.getDateDiscontinuation();

    Timestamp introduced = toTimestamp(date1);
    Timestamp discontinued = toTimestamp(date2);

    try (Connection connection = connectDb();
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
   * Sets the computer data.
   *
   * @param resultat    the resultat
   * @param companyList the company list
   * @return the computer
   * @throws SQLException the SQL exception
   */
  public Computer setComputerData(ResultSet resultat, List<Company> companyList)
      throws SQLException {
    Computer computer = new Computer();
    computer.setName(resultat.getString("name"));
    computer.setDateIntro(resultat.getTimestamp("introduced"));
    computer.setDateDiscontinuation(resultat.getTimestamp("discontinued"));
    computer.setId(resultat.getInt("id"));
    Company company = new Company();
    company.setId(resultat.getInt("company_id"));
    if (company.getId() != 0 && company.getId() < companyList.size() + 1) {

      company.setName(companyList.get(company.getId() - 1).getName());

    }
    computer.setCompany(company);

    return computer;
  }

  /**
   * Changes a date to a timestamp.
   *
   * @param date the date
   * @return the timestamp
   */
  public Timestamp toTimestamp(Date date) {
    if (null != date) {
      return new Timestamp(date.getTime());
    } else {
      return null;
    }

  }

}
