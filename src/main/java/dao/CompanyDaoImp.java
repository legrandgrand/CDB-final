package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyDaoImp implements CompanyDao {

  private static final String SELECT_ID = "SELECT id, name FROM company WHERE name LIKE ";
  private static final String SELECT_NAME = "SELECT id, name FROM company WHERE id= ";
  private static final String SELECT = "SELECT id, name FROM company";

  private static final Logger logger = LoggerFactory.getLogger(CompanyDaoImp.class);
  

  private DaoFactory factory = DaoFactory.getInstance();

  // TODO: stream
  private static final CompanyDaoImp instance = new CompanyDaoImp();

  public static final CompanyDaoImp getInstance() {
    return instance;
  }

  /**
   * Instantiates a new company dao imp.
   */
  private CompanyDaoImp() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.CompanyDao#listCompanies()
   */

  @Override
  public List<Company> list() {
    List<Company> list = new ArrayList<Company>();
    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {

      try (ResultSet resultat = statement.executeQuery(SELECT)) {

        while (resultat.next()) {
          String name = resultat.getString("name");
          int id = resultat.getInt("id");
          Company company = new Company(name, id);
          list.add(company);
        }
        
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Listed companies");
    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.CompanyDao#getCompany(java.lang.String)
   */
  @Override
  public List<Company> getCompany(Company company) {
    List<Company> list = new ArrayList<Company>();

    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement.executeQuery(SELECT_ID + "'%" + company.getName() + "%'");
      while (resultat.next()) {
        company.setName(resultat.getString("name"));
        company.setId(resultat.getInt("id"));
        list.add(company);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    logger.debug("Returning company: " + company);
    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.CompanyDao#getCompany(java.lang.String)
   */
  @Override
  public List<Company> getCompanyFromId(Company company) {
    List<Company> list = new ArrayList<Company>();

    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {
      ResultSet resultat = statement.executeQuery(SELECT_NAME + company.getId());
      while (resultat.next()) {
        company.setName(resultat.getString("name"));
        company.setId(resultat.getInt("id"));
        list.add(company);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    //logger.debug("Returning company: " + company);
    return list;
  }
}
