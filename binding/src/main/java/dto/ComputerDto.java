package dto;

public class ComputerDto {
  
  private long idComputer;
  private long idCompany; 
  private String name;// Compulsory
  private String intro;
  private String discontinuation;// Has to be higher than date B
  private String companyName;

  /**
   * Instantiates a new computer DTO.
   *
   * @param idComputer the id computer
   * @param name the name
   * @param intro the date intro
   * @param discontinuation the date discontinuation
   * @param companyName the company name
   * @param idCompany the id company
   */
  public ComputerDto(long idComputer, String name, String intro, String discontinuation,
      String companyName, long idCompany) {
    super();
    this.idComputer = idComputer;
    this.name = name;
    this.intro = intro;
    this.discontinuation = discontinuation;
    this.companyName = companyName;
    this.idCompany = idCompany;
  }
  
  public ComputerDto() {}
  
  public long getIdComputer() {
    return idComputer;
  }

  public void setIdComputer(long idComputer) {
    this.idComputer = idComputer;
  }
  
  public long getIdCompany() {
    return idCompany;
  }

  public void setIdCompany(long idCompany) {
    this.idCompany = idCompany;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public String getDiscontinuation() {
    return discontinuation;
  }

  public void setDiscontinuation(String discontinuation) {
    this.discontinuation = discontinuation;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
 
  @Override
  public String toString() {
    return "Name: " + name + "\n Introduction : " + intro + "\n Discontinuation: " + discontinuation
        + "\n Company: " + companyName;
  }
}
