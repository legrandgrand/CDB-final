package service;

import dao.ComputerDaoImp;
import exception.ComputerValidationException;

import java.util.List;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import validator.ComputerValidator;

@Service
public class ServiceComputer {

  private static final Logger logger = LoggerFactory.getLogger(ServiceComputer.class);
  
  @Autowired
  private ComputerValidator computerValidator;

  @Autowired
  private ComputerDaoImp computerDao;
  
  private ServiceComputer() {}

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
      computerValidator.validateName(computer.getName());
      computerValidator.validateId(computer.getId());
      computerDao.add(computer);
    } catch (ComputerValidationException e) {
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
      computerValidator.validateName(computer.getName());
      computerValidator.validateId(computer.getId());
      computerDao.update(computer);
    } catch (ComputerValidationException e) {
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
      computerValidator.validateName(computer.getName());
      logger.error("Valid computer name");
    } catch (ComputerValidationException e) {
      logger.error(e.getMessage(), e);
      return null;
    }
    return computerDao.getComputerFromName(computer);
  }

  /**
   * List page.
   *
   * @param limit the limit
   * @param page  the page
   * @return the list
   */
  public List<Computer> listPage(int limit, int page) {
    logger.debug("limit: " + limit + "page: " + page);
    return computerDao.listPage(limit, page);
  }

  /**
   * Return the max Id.
   * 
   * @return the max Id.
   */
  public int getMaxId() {
    return computerDao.getMaxId();
  }

  /**
   * Order by.
   *
   * @param column the column
   * @param type   the type
   * @param limit  the limit
   * @param page   the page
   * @return the list
   */
  public List<Computer> orderBy(String column, String type, int limit, int page) {
    return computerDao.orderBy(column, type, limit, page);
  }

}
