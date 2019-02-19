package model;

import java.util.Date;

public class Computer {

  private String name;// Compulsory
  private int nameManufacturer;// TODO: pas entier, mais company
  private Date dateIntro;
  private Date dateDiscontinuation;// Has to be higher than date B

  /**
   * Instantiates a new computer.
   *
   * @param name              the name PC
   * @param nameManufacturer    the name manufacturer
   * @param dateIntro           the date intro
   * @param dateDiscontinuation the date discontinuation
   */
  public Computer(String name, int nameManufacturer, Date dateIntro, Date dateDiscontinuation) {
    this.setName(name);
    this.setNameManufacturer(nameManufacturer);
    this.setDateIntro(dateIntro);
    this.setDateDiscontinuation(dateDiscontinuation);
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

  public int getNameManufacturer() {
    return nameManufacturer;
  }

  public void setNameManufacturer(int nameManuf) {
    this.nameManufacturer = nameManuf;
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
        + dateDiscontinuation + "\n Company Number: " + nameManufacturer;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
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
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

}
