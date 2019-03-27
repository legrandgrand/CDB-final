package service;

import dao.ComputerDaoImp;
import dto.ComputerDto;
import exception.ComputerValidationException;
import mapper.DtoMapper;

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
  private DtoMapper mapper;

  /**
   * Instantiates a new service computer.
   *
   * @param computerDao       the computer dao
   * @param computerValidator the computer validator
   */
  @Autowired
  public ComputerService(ComputerDaoImp computerDao, ComputerValidator computerValidator, DtoMapper mapper) {
    this.computerValidator = computerValidator;
    this.computerDao = computerDao;
    this.mapper = mapper;
  }

  /**
   * List.
   *
   * @return the list
   */
  public List<ComputerDto> list() {
    List<Computer> computers = computerDao.list();
    return mapper.listDtos(computers);
  }

  /**
   * Gets the computer.
   *
   * @param computer the computer
   * @return the computer
   */
  public List<ComputerDto> getComputer(int id) {
    List<Computer> computers = computerDao.getComputer(id);
    return mapper.listDtos(computers);
  }

  /**
   * Gets the computer from name.
   *
   * @param computer the computer
   * @return the computer from name
   */
  public List<ComputerDto> getComputerFromName(ComputerDto dto) {
    Computer computer = mapper.dtoToComputer(dto);
    List<Computer> computers = computerDao.getComputerFromName(computer);
    return mapper.listDtos(computers);
  }

  /**
   * List page.
   *
   * @param limit the limit
   * @param page  the page
   * @return the list
   */
  public List<ComputerDto> listPage(int limit, int page) {
    List<Computer> computers =  computerDao.listPage(limit, page);
    return mapper.listDtos(computers);
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
  public List<ComputerDto> orderBy(String column, String type, int limit, int page) {
    List<Computer> computers = computerDao.orderBy(column, type, limit, page);
    return mapper.listDtos(computers);
  }

  /**
   * Delete.
   *
   * @param computer the computer
   */
  public void delete(ComputerDto dto) {
    Computer computer = mapper.dtoToComputer(dto);
    computerDao.delete(computer);
  }

  /**
   * Adds the.
   *
   * @param computer the computer
   */
  public void add(ComputerDto dto) {
    try {
      Computer computer = mapper.dtoToComputer(dto);
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
  public void update(ComputerDto dto) {
    try {
      Computer computer = mapper.dtoToComputer(dto);
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
