package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

public class Controller {
  private ServiceComputer serviceComputer;
  private ServiceCompany serviceCompany;

  /**
   * Instantiates a new controller.
   * 
   * @param serviceComputer the service of the Computer
   * @param serviceCompany the service of the Company
   */
  public Controller(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
  }

  /**
   * List company.
   */
  public List<Company> listCompany() {
    return serviceCompany.listCompany();
  }

  /**
   * List computer.
   */
  public List<Computer> listComputer() {
    return serviceComputer.listComputer();
  }

  /**
   * Delete company.
   *
   * @param name the name
   */
  public void deleteComputer(String name) {
    serviceComputer.deleteComputer(name);
  }

  /**
   * Sets the computer name.
   *
   * @param sc the scanner
   * @return the ComputerName
   */
  public String setComputerName(Scanner sc) {
    return sc.nextLine();
  }

  /**
   * Sets the computer intro.
   *
   * @param sc the scanner
   * @return the timestamp
   */
  public Date setComputerIntro(Scanner sc) {
    Date intro = null;
    String timestamp = null;
    timestamp = sc.nextLine();
    if (!timestamp.equals("")) {
      intro = setDate(timestamp);
    }
    return intro;
  }

  /**
   * Sets the computer disc.
   *
   * @param sc    the scanner
   * @param intro the date of introduction
   * @return the date of discontinuation
   */
  public Date setComputerDisc(Scanner sc, Date intro) {
    Date discontinuation = null;
    String timestamp = null;
    do {
      timestamp = sc.nextLine();
      if (!timestamp.equals("")) {
        discontinuation = setDate(timestamp);
        if (null != intro) { // TODO: null.equals()null
          break;
        }
        if (discontinuation.before(intro)) {
          System.out.println("The date you entered happened before the date of introduction. "
              + "Please enter a valid date.");
        }
      } else {
        break;
      }
    } while (null != intro || discontinuation.before(intro));

    return discontinuation;

  }

  /**
   * Sets the computer company id.
   *
   * @param sc the scanner
   * @return the Company Id
   */
  public int setComputerCompanyId(Scanner sc) {
    return sc.nextInt();
  }

  /**
   * Adds the computer.
   *
   * @param name            the name
   * @param intro           the date of introduction
   * @param discontinuation the date of discontinuation
   * @param companyId       the company id
   * @return the computer
   */
  public Computer addComputer(String name, Date intro, Date discontinuation, int companyId) {
    Computer computer = new Computer(name, companyId, intro, discontinuation);
    serviceComputer.addComputer(computer);
    return computer;
  }

  /**
   * Update computer.
   *
   * @param name            the computer name
   * @param intro           the date of introduction
   * @param discontinuation the date of discontinuation
   * @param companyId       the company id
   * @return the computer
   */
  public Computer updateComputer(String name, Date intro, Date discontinuation, int companyId) {
    Computer computer = new Computer(name, companyId, intro, discontinuation);
    serviceComputer.updateComputer(computer);

    return computer;
  }

  /**
   * Sets the timestamp.
   *
   * @param timestamp the timestamp to change
   * @return the timestamp
   */
  public Date setDate(String timestamp) {
    timestamp = timestamp + " 00:00:00";// timestamp format: YYYY-MM-DD (user input) + 00:00:00
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    try {
      return dt.parse(timestamp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
