package mapper;

import dto.ComputerDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import model.Computer.ComputerBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
  
  private static final Logger logger = LoggerFactory.getLogger(Mapper.class);

  private Mapper() {}

  /**
   * Map dto to computer.
   *
   * @param dto the dto
   * @return the computer
   */
  public Computer dtoToComputer(ComputerDto dto) {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    ComputerBuilder computerBuilder = new ComputerBuilder().setName(dto.getName());
    Company company = new Company();
    company.setId(dto.getIdCompany());
    company.setName(dto.getCompanyName());
    computerBuilder.setCompany(company);

    try {
      if (!dto.getIdComputer().equals("")) {
        computerBuilder.setId(Integer.parseInt(dto.getIdComputer()));
      } else {
        computerBuilder.setId(0);
      }
      computerBuilder.setDateDiscontinuation(dt.parse(dto.getDateDiscontinuation()));
      computerBuilder.setDateIntro(dt.parse(dto.getDateIntro()));
    } catch (ParseException e) {
      logger.error(e.getMessage(), e);
    }
    return computerBuilder.build();
  }

  /**
   * Computer to dto.
   *
   * @param computer the computer
   * @return the computer dto
   */
  public ComputerDto computerToDto(Computer computer) {
    ComputerDto computerDto = new ComputerDto(computer.getId() + "", computer.getName(),
        dateToString(computer.getDateIntro()), dateToString(computer.getDateDiscontinuation()),
        computer.getCompany().getName(), computer.getCompany().getId());

    return computerDto;
  }

  /**
   * List dtos.
   *
   * @param computers the computers
   * @return the list
   */
  public List<ComputerDto> listDtos(List<Computer> computers) {
    List<ComputerDto> dtos = new ArrayList<ComputerDto>();
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
    CompanyBuilder companyBuilder = new CompanyBuilder().setNameCompany(dto.getCompanyName());

    companyBuilder.setCompanyId((dto.getIdCompany()));

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

}
