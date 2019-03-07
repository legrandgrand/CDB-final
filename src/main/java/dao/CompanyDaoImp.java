package dao;

import config.SpringConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyDaoImp implements CompanyDao {

  private static final String SELECT_ID = "SELECT id, name FROM company WHERE name LIKE ";
  private static final String SELECT_NAME = "SELECT id, name FROM company WHERE id= ";
  private static final String SELECT = "SELECT id, name FROM company";
  private static final String DELETE_COMPANY = "DELETE FROM company WHERE id= ? ";
  private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id= ? ";

  private static final Logger logger = LoggerFactory.getLogger(CompanyDaoImp.class);

  private SpringConfig database = new SpringConfig();

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
    try (Connection connection = database.connectDb();
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

    try (Connection connection = database.connectDb();
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

    try (Connection connection = database.connectDb();
        Statement statement = connection.createStatement();
        ResultSet resultat = statement.executeQuery(SELECT_NAME + company.getId())) {
      while (resultat.next()) {
        company.setName(resultat.getString("name"));
        company.setId(resultat.getInt("id"));
        list.add(company);
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    // logger.debug("Returning company: " + company);
    return list;
  }

  @Override
  @Transactional
  public void delete(Company company) {
    String idString = Integer.toString(company.getId());
    Connection connection = null;
    try {
      connection = database.connectDb();
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(DELETE_COMPANY);
      PreparedStatement statement2 = connection.prepareStatement(DELETE_COMPUTER);

      statement2.setString(1, idString);
      statement2.executeUpdate();

      statement.setString(1, idString);
      statement.executeUpdate();

      connection.commit();
      logger.debug("Deleted company of id:" + company.getId());

      statement.close();
      statement2.close();
      connection.close();
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
      try {
        if (connection != null) {
          logger.debug("Rolling back");
          connection.rollback();
        }
      } catch (SQLException se2) {
        logger.error(e.getMessage(), e);
      }
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException se) {
        logger.error(se.getMessage(), se);
      }
    }
  }

}
