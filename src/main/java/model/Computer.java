package model;

import java.util.Date;

public class Computer {

  private String name;// Compulsory
  private Company company;// TODO: pas entier, mais company
  private Date dateIntro;
  private Date dateDiscontinuation;// Has to be higher than date B
  private int id;

  /**
   * Instantiates a new computer.
   *
   * @param name                the name PC
   * @param company             the company
   * @param dateIntro           the date intro
   * @param dateDiscontinuation the date discontinuation
   * @param id                  the id
   */
  public Computer(String name, Company company, Date dateIntro, Date dateDiscontinuation, int id) {
    this.setName(name);
    this.setCompany(company);
    this.setDateIntro(dateIntro);
    this.setDateDiscontinuation(dateDiscontinuation);
    this.setId(id);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Instantiates a new computer.
   */
  public Computer() {
  }

  public Date getDateIntro() {
    return dateIntro;
  }

  public void setDateIntro(Date dateIntro2) {
    this.dateIntro = dateIntro2;
  }

  public Date getDateDiscontinuation() {
    return dateDiscontinuation;
  }

  public void setDateDiscontinuation(Date dateF) {
    this.dateDiscontinuation = dateF;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Name: " + name + "\n Introduction : " + dateIntro + "\n Discontinuation: "
        + dateDiscontinuation + "\n Company: " + company;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((company == null) ? 0 : company.hashCode());
    result = prime * result + ((dateDiscontinuation == null) ? 0 : dateDiscontinuation.hashCode());
    result = prime * result + ((dateIntro == null) ? 0 : dateIntro.hashCode());
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Computer other = (Computer) obj;
    if (company == null) {
      if (other.company != null) {
        return false;
      }
    } else if (!company.equals(other.company)) {
      return false;
    }
    if (dateDiscontinuation == null) {
      if (other.dateDiscontinuation != null) {
        return false;
      }
    } else if (!dateDiscontinuation.equals(other.dateDiscontinuation)) {
      return false;
    }
    if (dateIntro == null) {
      if (other.dateIntro != null) {
        return false;
      }
    } else if (!dateIntro.equals(other.dateIntro)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  public static class ComputerBuilder {
    private String name;// Compulsory
    private Company company;// TODO: pas entier, mais company
    private Date dateIntro;
    private Date dateDiscontinuation;// Has to be higher than date B
    private int id;

    /**
     * Builds the Computer.
     *
     * @return the computer
     */
    public Computer build() {
      Computer computer = new Computer();

      computer.setId(this.id);
      computer.setCompany(this.company);
      computer.setDateIntro(this.dateIntro);
      computer.setDateDiscontinuation(this.dateDiscontinuation);
      computer.setName(this.name);

      return computer;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     * @return the computer builder
     */
    public ComputerBuilder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the company.
     *
     * @param company the company
     * @return the computer builder
     */
    public ComputerBuilder setCompany(Company company) {
      this.company = company;
      return this;
    }

    /**
     * Sets the date intro.
     *
     * @param dateIntro the date intro
     * @return the computer builder
     */
    public ComputerBuilder setDateIntro(Date dateIntro) {
      this.dateIntro = dateIntro;
      return this;
    }

    /**
     * Sets the date discontinuation.
     *
     * @param dateDiscontinuation the date discontinuation
     * @return the computer builder
     */
    public ComputerBuilder setDateDiscontinuation(Date dateDiscontinuation) {
      this.dateDiscontinuation = dateDiscontinuation;
      return this;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     * @return the computer builder
     */
    public ComputerBuilder setId(int id) {
      this.id = id;
      return this;
    }

  }

}
