package dao;

import java.util.List;

import model.Company;
import model.Computer;
import model.Page;

public interface ComputerDao {

  /**
   * List computers.
   *
   * @return the list
   */
  public List<Computer> list();
  
  /**
   * Gets the computer from name.
   *
   * @param computer the computer
   * @return the computer from name
   */
  List<Computer> getComputerFromName(Computer computer);
  
  List<Computer> getComputer(long id);

  List<Computer> orderBy(Page page);

  List<Computer> listPage(Page page);

  /**
   * Delete computer.
   *
   * @param computer the computer
   */
  public void delete(Computer computer);

  /**
   * Update computer.
   *
   * @param computer the computer
   */
  public void update(Computer computer);

  /**
   * Adds the computer.
   *
   * @param computer the computer
   */
  public void add(Computer computer);

  public Long getMaxId();

  public void deleteComputerOfCompanyId(Company company);
  
}
