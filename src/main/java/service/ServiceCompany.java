package service;

import dao.CompanyDaoImp;

import java.util.List;

import model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import validator.Validator;

public class ServiceCompany {

  private static final ServiceCompany instance = new ServiceCompany();
  private static final Logger logger = LoggerFactory.getLogger(ServiceCompany.class);
  
  private CompanyDaoImp companyDao = CompanyDaoImp.getInstance(); 

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
    return companyDao.list();
  }

  /**
   * Gets the company.
   *
   * @param company the company
   * @return the company
   */
  public List<Company> getCompany(Company company) {
    return companyDao.getCompany(company);
  }

  /**
   * Gets the company from id.
   *
   * @param company the company
   * @return the company from id
   */
  public List<Company> getCompanyFromId(Company company) {
    try {
      Validator.validateId(company.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return companyDao.getCompanyFromId(company);
  }
  
  public void delete(Company company) {
    companyDao.delete(company);  
  }
}