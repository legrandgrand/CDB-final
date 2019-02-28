package mapper;

import dto.ComputerDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import model.Computer.ComputerBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mapper {
  private static final Mapper instance = new Mapper();
  private static final Logger logger = LoggerFactory.getLogger(Mapper.class);

  public static final Mapper getInstance() {
    return instance;
  }

  /**
   * Instantiates a new company dao imp.
   */
  private Mapper() {
  }

  /**
   * Map computer.
   *
   * @param dto the dto
   * @return the computer
   */
  public Computer mapComputer(ComputerDto dto) {
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

}
