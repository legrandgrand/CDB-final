package service;

import dao.DaoFactory;
import java.util.List;
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

}