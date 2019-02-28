package service;

import dao.ComputerDaoImp;
import java.util.List;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import validator.Validator;

public class ServiceComputer {

  private static final Logger logger = LoggerFactory.getLogger(ServiceComputer.class);
  private static final ServiceComputer instance = new ServiceComputer();

  private ComputerDaoImp computerDao = ComputerDaoImp.getInstance();

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
   * @param computer the computer
   */
  public void delete(Computer computer) {
    computerDao.delete(computer);
  }

  /**
   * List computer.
   *
   * @return the list
   */
  public List<Computer> list() {
    return computerDao.list();
  }

  /**
   * Adds the computer.
   *
   * @param computer the computer
   */
  public void add(Computer computer) {
    try {
      Validator.validateName(computer.getName());
      Validator.validateId(computer.getId());
      computerDao.add(computer);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * Update computer.
   *
   * @param computer the computer
   */
  public void update(Computer computer) {
    try {
      Validator.validateName(computer.getName());
      Validator.validateId(computer.getId());
      computerDao.update(computer);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * Gets the computer.
   *
   * @param computer the computer
   * @return the computer
   */
  public List<Computer> getComputer(Computer computer) {
    return computerDao.getComputer(computer);
  }

  /**
   * Gets the computer from name.
   *
   * @param computer the computer
   * @return the computer from name
   */
  public List<Computer> getComputerFromName(Computer computer) {
    try {
      Validator.validateName(computer.getName());
      logger.error("Valid computer name");
    } catch (Exception e) {
      logger.error("Invalid computer name");
      return null;
    }
    return computerDao.getComputerFromName(computer);
  }

  /**
   * List page.
   *
   * @param page the page
   * @return the list
   */
  public List<Computer> listPage(int page) {
    return computerDao.listPage(page);
  }

}
