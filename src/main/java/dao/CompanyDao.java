package dao;

import java.util.List;

import model.Company;

public interface CompanyDao {
  /**
   * List companies.
   *
   * @return the list
   */
  public List<Company> list();

  /**
   * Gets the company.
   *
   * @param name the Company name
   * @return the company
   */
  public int getCompany(String name);
}
