package dao;

import java.util.List;

import model.Company;

/**
 * The Interface CompanyDao.
 */
public interface CompanyDao{
	
	/**
	 * List companies.
	 *
	 * @return the list
	 */
	public List<Company> listCompanies();
	
	/**
	 * Gets the company.
	 *
	 * @param i the name
	 * @return the company
	 */
	public int getCompany(String name);
}
