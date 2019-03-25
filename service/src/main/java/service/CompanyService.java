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
@Transactional
public class CompanyService {

  private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

  private ComputerValidator computerValidator;
  private CompanyDaoImp companyDaoImp;
  private ComputerDaoImp computerDaoImp;

  /**
   * Instantiates a new service company.
   *
   * @param computerDaoImp the computer dao imp
   * @param companyDaoImp the company dao imp
   * @param computerValidator the computer validator
   */
  @Autowired
  public CompanyService(ComputerDaoImp computerDaoImp, CompanyDaoImp companyDaoImp,
      ComputerValidator computerValidator) {
    this.companyDaoImp = companyDaoImp;
    this.computerDaoImp = computerDaoImp;
    this.computerValidator = computerValidator;
  }

  /**
   * List company.
   *
   * @return the list
   */
  public List<Company> listCompany() {
    return companyDaoImp.list();
  }

  /**
   * Gets the company.
   *
   * @param company the company
   * @return the company
   */
  public List<Company> getCompany(Company company) {
    return companyDaoImp.getCompany(company);
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
    return companyDaoImp.getCompanyFromId(company);
  }

  /**
   * Delete.
   *
   * @param company the company
   */
  public void delete(Company company) {
    computerDaoImp.deleteComputerOfCompanyId(company);
    companyDaoImp.delete(company);
  }
}