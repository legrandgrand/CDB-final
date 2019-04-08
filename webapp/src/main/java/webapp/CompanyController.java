package webapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDto;
import exception.ComputerValidationException;
import service.CompanyService;

@RestController
public class CompanyController {
  private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

  private CompanyService serviceCompany;

  @Autowired
  public CompanyController(CompanyService serviceCompany) {
    this.serviceCompany = serviceCompany;
  }
  
  @GetMapping("/company/{id}")
  public CompanyDto getCompany(@PathVariable int id) {//REST Endpoint.
    CompanyDto dto = new CompanyDto();
    
    try {
      dto = serviceCompany.getCompanyFromId(id).get(0);
    } catch (ComputerValidationException invalidCompany) {
      logger.error(invalidCompany.getMessage(), invalidCompany);
    }
    
    return dto;
  }
  
  @GetMapping("/company/{id}")
  public List<CompanyDto> getAll(@PathVariable int id) {//REST Endpoint.
    
    List<CompanyDto> dto = serviceCompany.listCompany();
    return dto;
  }

}
