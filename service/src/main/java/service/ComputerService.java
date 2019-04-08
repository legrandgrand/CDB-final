package service;

import dao.ComputerDaoImp;
import dto.ComputerDto;
import exception.ComputerValidationException;
import mapper.ComputerMapper;

import java.util.List;

import model.Computer;
import model.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import validator.ComputerValidator;

@Service
@Transactional
public class ComputerService {

  private ComputerValidator computerValidator;
  private ComputerDaoImp computerDao;
  private ComputerMapper mapper;

  /**
   * Instantiates a new service computer.
   *
   * @param computerDao       the computer dao
   * @param computerValidator the computer validator
   */
  @Autowired
  public ComputerService(ComputerDaoImp computerDao, ComputerValidator computerValidator, ComputerMapper mapper) {
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
    return mapper.listDtos(computerDao.list());
  }

  /**
   * Gets from id.
   *
   * @param computer the computer
   * @return the computer
   */
  public List<ComputerDto> getFromId(long id) {
    return mapper.listDtos(computerDao.getComputer(id));
  }

  /**
   * Gets from name.
   *
   * @param computer the computer
   * @return the computer from name
   */
  public List<ComputerDto> getFromName(ComputerDto dto) throws ComputerValidationException {
    return mapper.listDtos(computerDao.getComputerFromName(mapper.dtoToComputer(dto)));
  }

  /**
   * List page.
   *
   * @param limit the limit
   * @param page  the page
   * @return the list
   */
  public List<ComputerDto> listPage(Page page) {
    return mapper.listDtos(computerDao.listPage(page));
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
  public List<ComputerDto> orderBy(Page page) {
    return mapper.listDtos(computerDao.orderBy(page));
  }

  /**
   * Delete.
   *
   * @param computer the computer
   */
  public void delete(ComputerDto dto) throws ComputerValidationException {
    computerDao.delete(mapper.dtoToComputer(dto));
  }

  /**
   * Adds the computer.
   *
   * @param computer the computer
   */
  public void add(ComputerDto dto) throws ComputerValidationException {
      Computer computer = mapper.dtoToComputer(dto);
      computerValidator.validateDiscBeforeIntro(computer.getIntro(), computer.getDiscontinuation());
      computerDao.add(computer);
  }

  /**
   * Update the computer.
   *
   * @param computer the computer
   */
  public void update(ComputerDto dto) throws ComputerValidationException {
      Computer computer = mapper.dtoToComputer(dto);
      computerValidator.validateDiscBeforeIntro(computer.getIntro(), computer.getDiscontinuation());
      computerDao.update(computer);
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
