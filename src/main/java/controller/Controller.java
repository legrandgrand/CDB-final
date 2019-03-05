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

import validator.Validator;

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
   *
   * @return the list
   */
  public List<Company> listCompany() {
    logger.debug("Listing companies");
    return serviceCompany.listCompany();
  }

  /**
   * List computer.
   *
   * @return the list
   */
  public List<Computer> listComputer() {
    logger.debug("Listing computers");
    return serviceComputer.list();
  }

  /**
   * Delete computer.
   *
   * @param name the name
   */
  public void deleteComputer(String name) {
    Computer computer = new Computer();
    computer.setName(name);
    logger.debug("Deleting computer named" + name);
    serviceComputer.delete(computer);
  }
  
  public void deleteCompany(int id) {
    Company company = new Company();
    company.setId(id);
    logger.debug("Deleting company of id: " + id);
    serviceCompany.delete(company);
  }

  /**
   * Sets the computer name.
   *
   * @param sc the scanner
   * @return the ComputerName
   * @throws Exception exception
   */
  public String setComputerName(Scanner sc) throws Exception {
    String name = sc.nextLine();
    try {
      Validator.validateName(name);
      logger.debug("Setting computer name: " + name);
      return name;
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  /**
   * Sets the computer intro.
   *
   * @param sc the scanner
   * @return the timestamp
   * @throws Exception the exception
   */
  public Date setComputerIntro(Scanner sc) throws Exception {
    Date intro = null;
    String timestamp = null;
    timestamp = sc.nextLine();
    try {
      Validator.validateIntro(timestamp);
      if (!timestamp.equals("")) {
        intro = setDate(timestamp);
      }
    } catch (Exception e) {
      throw new Exception(e);
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
   * @throws Exception the exception
   */
  public Date setComputerDisc(Scanner sc, Date intro) throws Exception {
    Date discontinuation = null;
    String timestamp = null;
    do {
      timestamp = sc.nextLine();
      try { 
        Validator.validateDisc(timestamp, intro);
        if (!timestamp.equals("")) {
          discontinuation = setDate(timestamp);
          if (null != intro) {
            break;
          }
          if (discontinuation.before(intro)) {
            logger.info("The date you entered happened before the date of introduction. "
                + "Please enter a valid date.");
          }
        } else {
          break;
        }
      } catch (Exception e) {
        throw new Exception(e);
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
    Company company = new Company();
    company.setName(sc.nextLine());
    logger.debug("Setting company Id: " + company.getName());
    company = (Company) serviceCompany.getCompany(company).get(0);
    //sc.nextLine();
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
      logger.error(e.getMessage(), e);
    }
    return null;
  }

}
