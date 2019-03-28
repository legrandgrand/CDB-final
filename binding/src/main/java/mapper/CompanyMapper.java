package mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.CompanyDto;

import model.Company;
import model.Company.CompanyBuilder;

import validator.ComputerValidator;

@Component
public class CompanyMapper {

  ComputerValidator validator;

  /**
   * Instantiates a new company mapper.
   */
  @Autowired
  private CompanyMapper(ComputerValidator validator) {
    this.validator = validator;
  }

  /**
   * Map company.
   *
   * @param dto the dto
   * @return the company
   */
  public Company dtoToCompany(CompanyDto dto) {
    CompanyBuilder companyBuilder = new CompanyBuilder()
        .setName(dto.getName())
        .setId(dto.getId());

    return companyBuilder.build();

  }

  public CompanyDto companyToDto(Company company) {
    CompanyDto dto = new CompanyDto();

    dto.setId(company.getId());
    dto.setName(company.getName());

    return dto;
  }
  
  /**
   * List dtos.
   *
   * @param computers the computers
   * @return the list
   */
  public List<CompanyDto> listDtos(List<Company> companies) {
    List<CompanyDto> dtos = new ArrayList<>();
    
    for (Company company : companies) {
      dtos.add(companyToDto(company));
    }
    
    return dtos;
  }

}
