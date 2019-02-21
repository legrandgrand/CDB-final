package service;

import dao.DaoFactory;
import java.util.List;
import java.util.Optional;

import model.Company;

public class ServiceCompany {

  private static final ServiceCompany instance = new ServiceCompany();

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

  public int getCompany(String companyIdString) {
    // TODO Auto-generated method stub
    return DaoFactory.getCompanyDao().getCompany(companyIdString);
  }

}