package service;

import java.util.List;

import dao.DaoFactory;
import model.Company;
import model.Computer;

/**
 * The Class Service.
 */
public class Service {
	
	/**
	 * Instantiates a new service.
	 */
	public Service () {}
	
	/**
	 * Delete company.
	 *
	 * @param name the name
	 */
	public void deleteComputer(String name){
		DaoFactory.getComputerDao().deleteComputer(name);
	}
	
	/**
	 * List computer.
	 *
	 * @return the list
	 */
	public List<Computer> listComputer() {
		return  DaoFactory.getComputerDao().listComputers();
	}
	
	/**
	 * List company.
	 *
	 * @return the list
	 */
	public List<Company>  listCompany() {
		return DaoFactory.getCompanyDao().listCompanies();
	}

	/**
	 * Adds the computer.
	 *
	 * @param computer the computer
	 */
	public void addComputer(Computer computer) {
		DaoFactory.getComputerDao().addComputer(computer);
		
	}

	/**
	 * Update computer.
	 *
	 * @param computer the computer
	 */
	public void updateComputer(Computer computer) {
		DaoFactory.getComputerDao().updateComputer(computer);	
	}
	

}
