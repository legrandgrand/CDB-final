package service;

import dao.CompanyDaoImp;
import dao.ComputerDaoImp;

import java.util.List;

import model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import validator.ComputerValidator;

@Service
public class ServiceCompany {

  private static final Logger logger = LoggerFactory.getLogger(ServiceCompany.class);
  
  @Autowired
  private ComputerValidator computerValidator;
  
  @Autowired
  private CompanyDaoImp companyDao; 
  
  @Autowired
  private ComputerDaoImp computerDao; 

  private ServiceCompany() {}

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
      computerValidator.validateId(company.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return companyDao.getCompanyFromId(company);
  }
  
  @Transactional
  public void delete(Company company) {
    computerDao.deleteComputerOfCompanyId(company);
    companyDao.delete(company);  
  }
}