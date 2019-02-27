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
   * Delete company.
   *
   * @param name the name
   */
  public void deleteComputer(String name) {
    DaoFactory.getComputerDao().delete(name);
  }

  /**
   * List computer.
   *
   * @return the list
   */
  public List<Computer> listComputer() {
    return DaoFactory.getComputerDao().list();
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
