package dao;

import java.util.List;

import model.Computer;

/**
 * The Interface ComputerDao.
 */
public interface ComputerDao {
	
	/**
	 * List computers.
	 *
	 * @return the list
	 */
	public List<Computer> listComputers();
	
	/**
	 * Delete computer.
	 *
	 * @param computerName the computer name
	 */
	public void deleteComputer(String computerName);
	
	/**
	 * Update computer.
	 *
	 * @param computer the computer
	 */
	public void updateComputer(Computer computer);	
	
	/**
	 * Adds the computer.
	 *
	 * @param computer the computer
	 */
	public void addComputer(Computer computer);	

}
