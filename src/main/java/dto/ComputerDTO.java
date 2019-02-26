package dto;

import java.util.Date;

import model.Company;

public class ComputerDTO {
  
  private String name;// Compulsory
  private Date dateIntro;
  private Date dateDiscontinuation;// Has to be higher than date B
  private Company company;
  
  private static final ComputerDTO instance = new ComputerDTO();
  
  /**
   * Instantiates a new computer dao imp.
   */
  private ComputerDTO() {
  }

  public static final ComputerDTO getInstance() {
    return instance;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Date getDateIntro() {
    return dateIntro;
  }

  public void setDateIntro(Date dateIntro) {
    this.dateIntro = dateIntro;
  }

  public Date getDateDiscontinuation() {
    return dateDiscontinuation;
  }

  public void setDateDiscontinuation(Date dateDiscontinuation) {
    this.dateDiscontinuation = dateDiscontinuation;
  }

  public int getComputerId() {
    return computerId;
  }

  public void setComputerId(int computerId) {
    this.computerId = computerId;
  }

  public String getNameCompany() {
    return nameCompany;
  }

  public void setNameCompany(String nameCompany) {
    this.nameCompany = nameCompany;
  }

  private int computerId;
  private String nameCompany;

  @Override
  public String toString() {
    return "ComputerDTO [name=" + name + ", dateIntro=" + dateIntro + ", dateDiscontinuation="
        + dateDiscontinuation + ", company=" + company + ", computerId=" + computerId
        + ", nameCompany=" + nameCompany + "]";
  }

}
