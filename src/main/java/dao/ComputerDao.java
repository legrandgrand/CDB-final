package dao;

import java.util.List;

import model.Computer;

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
   * @param computerName the computer name
   */
  public void delete(String computerName);

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
  
  public List<Computer> getComputer(int id);

  public List<Computer> listPage(int page);

  List<Computer> getComputerFromName(String name);

}
