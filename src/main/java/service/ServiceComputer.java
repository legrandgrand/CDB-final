package service;

import dao.DaoFactory;
import java.util.List;
import model.Computer;

public class ServiceComputer {

  private static final ServiceComputer instance = new ServiceComputer();

  private ServiceComputer() {
  }

  public static ServiceComputer getInstance() {
    return instance;
  }

  /**
   * Delete computer.
   *
   * @param name the name
   */
  public void delete(String name) {
    DaoFactory.getComputerDao().delete(name);
  }

  /**
   * List computer.
   *
   * @return the list
   */
  public List<Computer> list() {
    return DaoFactory.getComputerDao().list();
  }

  /**
   * Adds the computer.
   *
   * @param computer the computer
   */
  public void add(Computer computer) {
    DaoFactory.getComputerDao().add(computer);

  }

  /**
   * Update computer.
   *
   * @param computer the computer
   */
  public void update(Computer computer) {
    DaoFactory.getComputerDao().update(computer);
  }
  
  public List<Computer> getComputer(int id) {
    return DaoFactory.getComputerDao().getComputer(id);
  }
  
  public List<Computer> listPage(int page) {
    return DaoFactory.getComputerDao().listPage(page);
  }

}
