package model;

import java.util.Date;

public class Computer {

  private String name;// Compulsory
  private Company company;
  private Date intro;
  private Date discontinuation;// Has to be higher than date B
  private int id;

  /**
   * Instantiates a new computer.
   *
   * @param name the name
   * @param company the company
   * @param intro the intro
   * @param discontinuation the discontinuation
   * @param id the id
   */
  public Computer(String name, Company company, Date intro, Date discontinuation, int id) {
    this.setName(name);
    this.setCompany(company);
    this.setIntro(intro);
    this.setDiscontinuation(discontinuation);
    this.setId(id);
  }
  
  /**
   * Instantiates a new computer.
   */
  public Computer() {
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getIntro() {
    return intro;
  }

  public void setIntro(Date intro) {
    this.intro = intro;
  }

  public Date getDiscontinuation() {
    return discontinuation;
  }

  public void setDiscontinuation(Date date) {
    this.discontinuation = date;
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Name: " + name + "\n Introduction : " + intro + "\n Discontinuation: "
        + discontinuation + "\n Company: " + company;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((company == null) ? 0 : company.hashCode());
    result = prime * result + ((discontinuation == null) ? 0 : discontinuation.hashCode());
    result = prime * result + ((intro == null) ? 0 : intro.hashCode());
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
    if (discontinuation == null) {
      if (other.discontinuation != null) {
        return false;
      }
    } else if (!discontinuation.equals(other.discontinuation)) {
      return false;
    }
    if (intro == null) {
      if (other.intro != null) {
        return false;
      }
    } else if (!intro.equals(other.intro)) {
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
    private Company company;
    private Date intro;
    private Date discontinuation;// Has to be higher than date B
    private int id;

    /**
     * Builds the.
     *
     * @return the computer
     */
    public Computer build() {
      Computer computer = new Computer();

      computer.setId(this.id);
      computer.setCompany(this.company);
      computer.setIntro(this.intro);
      computer.setDiscontinuation(this.discontinuation);
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
     * Sets the intro.
     *
     * @param dateIntro the date intro
     * @return the computer builder
     */
    public ComputerBuilder setIntro(Date dateIntro) {
      this.intro = dateIntro;
      return this;
    }

    /**
     * Sets the discontinuation.
     *
     * @param discontinuation the discontinuation
     * @return the computer builder
     */
    public ComputerBuilder setDiscontinuation(Date discontinuation) {
      this.discontinuation = discontinuation;
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
