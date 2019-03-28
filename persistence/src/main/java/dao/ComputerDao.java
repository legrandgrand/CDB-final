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
 

  /**
   * List page.
   *
   * @param page the page
   * @return the list
   */
  public List<Computer> listPage(int limit, int page);

  /**
   * Gets the computer from name.
   *
   * @param computer the computer
   * @return the computer from name
   */
  List<Computer> getComputerFromName(Computer computer);

  Long getMaxId();

  void deleteComputerOfCompanyId(Company company);

  List<Computer> getComputer(int id);

  List<Computer> orderBy(Page page);



}
