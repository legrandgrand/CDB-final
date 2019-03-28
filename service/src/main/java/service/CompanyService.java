package service;

import dao.CompanyDaoImp;
import dao.ComputerDaoImp;
import dto.CompanyDto;
import mapper.CompanyMapper;

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
  private CompanyMapper companyMapper;

  /**
   * Instantiates a new service company.
   *
   * @param computerDaoImp the computer dao imp
   * @param companyDaoImp the company dao imp
   * @param computerValidator the computer validator
   */
  @Autowired
  public CompanyService(ComputerDaoImp computerDaoImp, CompanyDaoImp companyDaoImp,
      ComputerValidator computerValidator, CompanyMapper companyMapper) {
    this.companyDaoImp = companyDaoImp;
    this.computerDaoImp = computerDaoImp;
    this.computerValidator = computerValidator;
    this.companyMapper = companyMapper;
  }

  /**
   * List company.
   *
   * @return the list
   */
  public List<CompanyDto> listCompany() {  
     return companyMapper.listDtos(companyDaoImp.list());
  }

  /**
   * Gets the company.
   *
   * @param company the company
   * @return the company
   */
  public List<CompanyDto> getCompany(CompanyDto companyDto) {
    return companyMapper.listDtos(companyDaoImp.getCompany(companyMapper.dtoToCompany(companyDto)));
  }

  /**
   * Gets the company from id.
   *
   * @param company the company
   * @return the company from id
   */
  public List<CompanyDto> getCompanyFromId(CompanyDto companyDto) {//TODO: shouldn't validate here
    try {
      computerValidator.validateId(companyDto.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return companyMapper.listDtos(companyDaoImp.getCompanyFromId(companyMapper.dtoToCompany(companyDto)));
  }

  /**
   * Delete.
   *
   * @param company the company
   */
  public void delete(CompanyDto companyDto) {
    Company company = companyMapper.dtoToCompany(companyDto);
    computerDaoImp.deleteComputerOfCompanyId(company);
    companyDaoImp.delete(company);
  }
}