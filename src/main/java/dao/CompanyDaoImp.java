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

import org.slf4j.Logger;

public class CompanyDaoImp implements CompanyDao {

  /** The Constant SELECT_ID. */
  private static final String SELECT_ID = "SELECT id FROM company WHERE name = ?";
  
  /** The Constant SELECT. */
  private static final String SELECT = "SELECT id, name FROM company";

  /** The logger. */
  Logger logger = null;

  // TODO Pagination
  /** The Constant instance. */
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
    DaoFactory factory = DaoFactory.getInstance();
    try (Connection connection = factory.connectDb();
        Statement statement = connection.createStatement()) {

      try (ResultSet resultat = statement.executeQuery(SELECT)) {

        while (resultat.next()) {
          String name = resultat.getString("name");
          Company company = new Company(name);
          list.add(company);

        }
      }
    } catch (SQLException e) {

      logger.debug("An error happened during the query");
    }
    return list;
  }

  /* (non-Javadoc)
   * @see dao.CompanyDao#getCompany(java.lang.String)
   */
  @Override
  public Optional<Integer> getCompany(String name) {
    DaoFactory factory = DaoFactory.getInstance();
    Optional<Integer> companyId = null;
    try (Connection connection = factory.connectDb();
        PreparedStatement statement = connection.prepareStatement(SELECT_ID)) {
      statement.setString(1, "name");
      ResultSet resultat = statement.executeQuery(SELECT_ID);
      if (resultat.next()) {
        companyId = Optional.ofNullable(resultat.getInt("id"));
      }
    } catch (SQLException e) {
      logger.debug("An error happened during the query");
    }
    return companyId;
  }
}
