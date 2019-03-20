package mapper;

import dto.ComputerDto;
import exception.ComputerValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import validator.ComputerValidator;

@Component
public class DtoMapper {

  private static final Logger logger = LoggerFactory.getLogger(DtoMapper.class);

  ComputerValidator validator;

  /**
   * Instantiates a new dto mapper.
   */
  @Autowired
  public DtoMapper(ComputerValidator validator) {
    this.validator = validator;
  }

  /**
   * Dto to computer.
   *
   * @param dto the dto
   * @return the computer
   */
  public Computer dtoToComputer(ComputerDto dto) {
    Computer computer = new Computer();
    try {
      validator.validateDto(dto);

      computer.setName(dto.getName());
      computer.setIntro(setDate(dto.getIntro()));
      computer.setDiscontinuation(setDate(dto.getDiscontinuation()));
      computer.setCompany(new Company(dto.getCompanyName(), dto.getIdCompany()));
    } catch (ComputerValidationException e) {
      logger.error(e.getMessage(), e);
    }

    return computer;
  }

  /**
   * Computer to dto.
   *
   * @param computer the computer
   * @return the computer dto
   */
  public ComputerDto computerToDto(Computer computer) {
    ComputerDto dto = new ComputerDto();

    dto.setIdComputer(computer.getId());
    dto.setName(computer.getName());
    dto.setIntro(dateToString(computer.getIntro()));
    dto.setDiscontinuation(dateToString(computer.getDiscontinuation()));
    
    if (computer.getCompany() != null) {
      dto.setCompanyName(computer.getCompany().getName());
      dto.setIdCompany(computer.getCompany().getId());
    }

    return dto;
  }

  /**
   * List dtos.
   *
   * @param computers the computers
   * @return the list
   */
  public List<ComputerDto> listDtos(List<Computer> computers) {
    List<ComputerDto> dtos = new ArrayList<>();
    for (Computer computer : computers) {
      dtos.add(computerToDto(computer));
    }
    return dtos;
  }

  /**
   * Map company.
   *
   * @param dto the dto
   * @return the company
   */
  public Company mapCompany(ComputerDto dto) {
    CompanyBuilder companyBuilder = new CompanyBuilder().setNameCompany(dto.getCompanyName())
        .setCompanyId(dto.getIdCompany());

    return companyBuilder.build();

  }

  /**
   * Date to string.
   *
   * @param date the date
   * @return the string
   */
  private String dateToString(Date date) {
    if (date != null) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return String.format("%d/%d/%d", calendar.get(Calendar.DAY_OF_MONTH),
          calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }
    return "";
  }

  /**
   * Sets the date.
   *
   * @param timestamp the timestamp
   * @return the date
   */
  public Date setDate(String timestamp) {
    timestamp = timestamp + " 00:00:00";// timestamp format: YYYY-MM-DD (user input) + 00:00:00
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    try {
      return dt.parse(timestamp);
    } catch (ParseException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

}
