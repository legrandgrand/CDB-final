package service;

import dao.ComputerDaoImp;
import exception.ComputerValidationException;

import java.util.List;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import validator.ComputerValidator;

@Service
@Transactional
public class ComputerService {

  private static final Logger logger = LoggerFactory.getLogger(ComputerService.class);

  private ComputerValidator computerValidator;
  private ComputerDaoImp computerDao;

  /**
   * Instantiates a new service computer.
   *
   * @param computerDao       the computer dao
   * @param computerValidator the computer validator
   */
  @Autowired
  public ComputerService(ComputerDaoImp computerDao, ComputerValidator computerValidator) {
    this.computerValidator = computerValidator;
    this.computerDao = computerDao;
  }

  /**
   * List.
   *
   * @return the list
   */
  public List<Computer> list() {
    return computerDao.list();
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
    return computerDao.listPage(limit, page);
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

  /**
   * Delete.
   *
   * @param computer the computer
   */
  public void delete(Computer computer) {
    computerDao.delete(computer);
  }

  /**
   * Adds the.
   *
   * @param computer the computer
   */
  public void add(Computer computer) {
    try {
      computerValidator.validateDiscBeforeIntro(computer.getIntro(), computer.getDiscontinuation());
      computerDao.add(computer);
    } catch (ComputerValidationException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * Update.
   *
   * @param computer the computer
   */
  public void update(Computer computer) {
    try {
      computerValidator.validateDiscBeforeIntro(computer.getIntro(), computer.getDiscontinuation());
      computerDao.update(computer);
    } catch (ComputerValidationException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * Return the max Id.
   * 
   * @return the max Id.
   */
  public Long getMaxId() {
    return computerDao.getMaxId();
  }

}
