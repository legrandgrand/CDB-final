package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
  
  private String name;
  private long id;

  public Company(String name, long id) {
    super();
    this.name = name;
    this.id = id;
  }

  public Company() {
  }


  @Id
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column
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
    private long id;

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

    public CompanyBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public CompanyBuilder setId(long id) {
      this.id = id;
      return this;
    }
  }

}
