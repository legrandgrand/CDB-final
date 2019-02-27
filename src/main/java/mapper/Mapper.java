package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import dto.ComputerDto;
import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import model.Computer.ComputerBuilder;
import service.ServiceCompany;

public class Mapper {
  private static final Mapper instance = new Mapper();

  public static final Mapper getInstance() {
    return instance;
  }

  /**
   * Instantiates a new company dao imp.
   */
  private Mapper() {
  }
  
  public Computer mapComputer(ComputerDto dto) {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    ComputerBuilder computerBuilder = new ComputerBuilder().setName(dto.getName());


    computerBuilder.setId(dto.getIdComputer());
    
    Company company = (Company) ServiceCompany.getInstance().getCompanyFromId(dto.getIdCompany());
    computerBuilder.setCompany(company);
    
    try {
      computerBuilder.setDateDiscontinuation(dt.parse(dto.getDateDiscontinuation()));
      computerBuilder.setDateIntro(dt.parse(dto.getDateIntro()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return computerBuilder.build();
  }
  
  public Company mapCompany(ComputerDto dto) {
    CompanyBuilder companyBuilder = new CompanyBuilder().setNameCompany(dto.getCompanyName());
    
    companyBuilder.setCompanyId(dto.getIdCompany());
   
    return companyBuilder.build();
    
  }
  
  

}
