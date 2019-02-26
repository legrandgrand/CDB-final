package dto;

public class ComputerDto {
  
  private String idComputer;
  private String name;// Compulsory
  private String dateIntro;
  private String dateDiscontinuation;// Has to be higher than date B
  private String companyName;
  private String companyId;  

  /**
   * Instantiates a new computer DTO.
   *
   * @param idComputer the id computer
   * @param name the name
   * @param dateIntro the date intro
   * @param dateDiscontinuation the date discontinuation
   * @param companyName the company name
   * @param companyId the company id
   */
  public ComputerDto(String idComputer, String name, String dateIntro, String dateDiscontinuation,
      String companyName, String companyId) {
    super();
    this.idComputer = idComputer;
    this.name = name;
    this.dateIntro = dateIntro;
    this.dateDiscontinuation = dateDiscontinuation;
    this.companyName = companyName;
    this.companyId = companyId;
  }
  
  public String getIdComputer() {
    return idComputer;
  }

  public void setIdComputer(String idComputer) {
    this.idComputer = idComputer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDateIntro() {
    return dateIntro;
  }

  public void setDateIntro(String dateIntro) {
    this.dateIntro = dateIntro;
  }

  public String getDateDiscontinuation() {
    return dateDiscontinuation;
  }

  public void setDateDiscontinuation(String dateDiscontinuation) {
    this.dateDiscontinuation = dateDiscontinuation;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ComputerDTO [idComputer=" + idComputer + ", name=" + name + ", dateIntro=" + dateIntro
        + ", dateDiscontinuation=" + dateDiscontinuation + ", companyName=" + companyName
        + ", companyId=" + companyId + "]";
  }

}
