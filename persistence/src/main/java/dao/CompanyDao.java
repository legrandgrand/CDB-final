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
   * @param company the company
   * @return the company
   */
  public List<Company> getCompany(Company company);
  
  /**
   * Gets the company from id.
   *
   * @param company the company
   * @return the company from id
   */
  public List<Company> getCompanyFromId(Company company);

  void delete(Company company);
}
