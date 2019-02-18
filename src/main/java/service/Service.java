package service;

import java.util.List;

import dao.DaoFactory;
import model.Company;
import model.Computer;

/**
 * The Class Service.
 */
public class Service {
	
	private static final Service instance= new Service();
	
	/**
	 * Instantiates a new service.
	 */
	private Service () {}
	
	public static Service getInstance() {
		return instance;
	}
	
	/**
	 * Delete company.
	 *
	 * @param name the name
	 */
	public void deleteComputer(String name){
		DaoFactory.getComputerDao().delete(name);
	}
	
	/**
	 * List computer.
	 *
	 * @return the list
	 */
	public List<Computer> listComputer() {
		return  DaoFactory.getComputerDao().list();
	}
	
	/**
	 * List company.
	 *
	 * @return the list
	 */
	public List<Company>  listCompany() {
		return DaoFactory.getCompanyDao().list();
	}

	/**
	 * Adds the computer.
	 *
	 * @param computer the computer
	 */
	public void addComputer(Computer computer) {
		DaoFactory.getComputerDao().add(computer);
		
	}

	/**
	 * Update computer.
	 *
	 * @param computer the computer
	 */
	public void updateComputer(Computer computer) {
		DaoFactory.getComputerDao().update(computer);	
	}
	

}
