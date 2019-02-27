package service;

import dao.DaoFactory;
import java.util.List;

import model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import validator.Validator;

public class ServiceCompany {

  private static final ServiceCompany instance = new ServiceCompany();
  private static final Logger logger = LoggerFactory.getLogger(ServiceComputer.class);

  /**
   * Instantiates a new service company.
   */
  private ServiceCompany() {
  }

  public static ServiceCompany getInstance() {
    return instance;
  }

  /**
   * List company.
   *
   * @return the list
   */
  public List<Company> listCompany() {
    return DaoFactory.getCompanyDao().list();
  }

  /**
   * Gets the company.
   *
   * @param companyIdString the company id string
   * @return the company
   */
  public List<Company> getCompany(String companyIdString) {
    return DaoFactory.getCompanyDao().getCompany(companyIdString);
  }

  /**
   * Gets the company from id.
   *
   * @param companyId the company id
   * @return the company from id
   */
  public List<Company> getCompanyFromId(int companyId) {
    if (Validator.validateId(companyId)) {
      return DaoFactory.getCompanyDao().getCompanyFromId(companyId);
    } else {
      logger.error("Invalid computer name");
      return null;
    }

  }
}