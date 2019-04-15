package webapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDto;

import exception.ComputerValidationException;
import service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyControllerRest {
  private static final Logger logger = LoggerFactory.getLogger(CompanyControllerRest.class);

  private CompanyService serviceCompany;

  @Autowired
  public CompanyControllerRest(CompanyService serviceCompany) {
    this.serviceCompany = serviceCompany;
  }

  @GetMapping("/")
  public List<CompanyDto> getAll() {

    List<CompanyDto> dto = serviceCompany.listCompany();
    return dto;

  }

  @GetMapping("/{id}")
  public CompanyDto getCompany(@PathVariable int id) {
    CompanyDto dto = new CompanyDto();

    try {
      dto = serviceCompany.getCompanyFromId(id).get(0);
      
    } catch (ComputerValidationException invalidCompany) {
      logger.error(invalidCompany.getMessage(), invalidCompany);
    }

    return dto;
  }

  @PostMapping("/delete")
  public ResponseEntity<CompanyDto> deleteComputer(CompanyDto dto) {

    serviceCompany.delete(dto);

    return new ResponseEntity<CompanyDto>(dto, HttpStatus.OK);
  }

}
