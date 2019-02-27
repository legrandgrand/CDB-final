package service;

import dao.DaoFactory;
import java.util.List;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import validator.Validator;

public class ServiceComputer {

  private static final Logger logger = LoggerFactory.getLogger(ServiceComputer.class);
  private static final ServiceComputer instance = new ServiceComputer();

  /**
   * Instantiates a new service computer.
   */
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
    if (Validator.validateName(computer.getName()) && Validator.validateId(computer.getId())) {
      DaoFactory.getComputerDao().add(computer);
    } else {
      logger.error("Invalid computer name");
    }

  }

  /**
   * Update computer.
   *
   * @param computer the computer
   */
  public void update(Computer computer) {
    if (Validator.validateName(computer.getName()) && Validator.validateId(computer.getId())) {
      DaoFactory.getComputerDao().update(computer);
    } else {
      logger.error("Invalid computer name");
    }
  }

  /**
   * Gets the computer.
   *
   * @param id the id
   * @return the computer
   */
  public List<Computer> getComputer(int id) {
    return DaoFactory.getComputerDao().getComputer(id);
  }

  /**
   * Gets the computer from name.
   *
   * @param name the name
   * @return the computer from name
   */
  public List<Computer> getComputerFromName(String name) {
    if (Validator.validateName(name)) {
      logger.error("Valid computer name");
      return DaoFactory.getComputerDao().getComputerFromName(name);
    } else {
      logger.error("Invalid computer name");
      return null;
    }
  }

  /**
   * List page.
   *
   * @param page the page
   * @return the list
   */
  public List<Computer> listPage(int page) {
    return DaoFactory.getComputerDao().listPage(page);
  }

}
