package model;

public class Company {
  private String name;
  private int id;

  /**
   * Instantiates a new company.
   *
   * @param name the name company
   * @param id   the company id
   */
  public Company(String name, int id) {
    super();
    this.name = name;
    this.id = id;
  }

  /**
   * Instantiates a new company.
   */
  public Company() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  
  @Override
  public String toString() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    Company other = (Company) obj;
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

  public static class CompanyBuilder {
    private String name;
    private int id;

    /**
     * Builds the company.
     *
     * @return the company
     */
    public Company build() {
      Company company = new Company();

      company.setId(this.id);
      company.setName(this.name);

      return company;
    }

    /**
     * Sets the name company.
     *
     * @param name the name
     * @return the company builder
     */
    public CompanyBuilder setNameCompany(String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the company id.
     *
     * @param id the id
     * @return the company builder
     */
    public CompanyBuilder setCompanyId(int id) {
      this.id = id;
      return this;
    }
  }

}
