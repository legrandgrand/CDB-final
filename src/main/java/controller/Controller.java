package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ServiceCompany;
import service.ServiceComputer;

public class Controller {
  private ServiceComputer serviceComputer;
  private ServiceCompany serviceCompany;
  private static final Logger logger = LoggerFactory.getLogger(Controller.class);

  /**
   * Instantiates a new controller.
   * 
   * @param serviceComputer the service of the Computer
   * @param serviceCompany  the service of the Company
   */
  public Controller(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
  }

  /**
   * List company.
   */
  public List<Company> listCompany() {
    logger.debug("Listing companies");
    return serviceCompany.listCompany();
  }

  /**
   * List computer.
   */
  public List<Computer> listComputer() {
    logger.debug("Listing computers");
    return serviceComputer.list();
  }

  /**
   * Delete company.
   *
   * @param name the name
   */
  public void deleteComputer(String name) {
    logger.debug("Deleting computer named" + name);
    serviceComputer.delete(name);
  }

  /**
   * Sets the computer name.
   *
   * @param sc the scanner
   * @return the ComputerName
   */
  public String setComputerName(Scanner sc) {
    String name = sc.nextLine();
    logger.debug("Setting computer name: " + name);
    return name;
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
    logger.debug("Setting computer date of introduction: " + intro);
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
          logger.info("The date you entered happened before the date of introduction. "
              + "Please enter a valid date.");
        }
      } else {
        break;
      }
    } while (null != intro || discontinuation.before(intro));
    logger.debug("Setting computer date of discontinuation: " + discontinuation);
    return discontinuation;

  }

  /**
   * Sets the computer company id.
   *
   * @param sc the scanner
   * @return the Company Id
   */
  public Company setComputerCompany(Scanner sc) {
    String companyId = sc.nextLine();
    logger.debug("Setting company Id: " + companyId);
    Company company = (Company) serviceCompany.getCompany(companyId).get(0);
    return company;
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
  public Computer addComputer(String name, Date intro, Date discontinuation, Company companyId) {
    Computer computer = new Computer(name, companyId, intro, discontinuation, 0);
    logger.debug("Adding computer: " + computer);
    serviceComputer.add(computer);
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
  public Computer updateComputer(String name, Date intro, Date discontinuation, Company companyId) {
    Computer computer = new Computer(name, companyId, intro, discontinuation, 0);
    logger.debug("Updating computer: " + computer);
    serviceComputer.update(computer);

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
      e.printStackTrace();
      logger.error("Parse Exception");
    }
    return null;
  }

}
