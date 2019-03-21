package controller;

import exception.ComputerValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.ServiceCompany;
import service.ServiceComputer;

import validator.ComputerValidator;
import view.View;

@Component
public class ControllerCli {

  private static final Logger logger = LoggerFactory.getLogger(ControllerCli.class);

  private View view;
  private ServiceComputer serviceComputer;
  private ServiceCompany serviceCompany;
  private ComputerValidator computerValidator;

  /**
   * Instantiates a new controller.
   *
   * @param computerValidator the computer validator
   * @param serviceCompany    the service company
   * @param serviceComputer   the service computer
   * @param view              the view
   */
  @Autowired
  public ControllerCli(ComputerValidator computerValidator, ServiceCompany serviceCompany,
      ServiceComputer serviceComputer, View view) {
    this.view = view;
    this.serviceCompany = serviceCompany;
    this.serviceComputer = serviceComputer;
    this.computerValidator = computerValidator;
  }

  /**
   * Start.
   */
  public void start() {
    view.start();
    mainMenu();
  }

  /**
   * Main menu.
   */
  public void mainMenu() {
    view.mainMenu();
    Scanner sc = new Scanner(System.in);

    try {
      int userChoice = sc.nextInt();
      switch (userChoice) { // TODO: enums
        case 1:
          listCompany();
          break;
        case 2:
          listComputers();
          break;
        case 3:
          addComputer(sc);
          break;
        case 4:
          deleteComputer(sc);
          break;
        case 5:
          deleteCompany(sc);
          break;
        case 6:
          updateComputer(sc);
          break;
        case 7:
          view.exit();
          sc.close();
          break;
        default:
          view.invalidInput();
          mainMenu();
          break;
      }
    } catch (InputMismatchException e) {
      view.invalidInput();
      mainMenu();
    }
  }

  /**
   * List company.
   */
  public void listCompany() {
    view.startListCompanies();
    logger.debug("Listing companies");
    List<Company> list = serviceCompany.listCompany();
    view.listCompanies(list);
    mainMenu();
  }

  /**
   * List computers.
   */
  public void listComputers() {
    view.startListComputers();
    logger.debug("Listing computers");
    List<Computer> list = serviceComputer.list();
    view.listComputers(list);
    mainMenu();
  }

  /**
   * Delete computer.
   */
  public void deleteComputer(Scanner sc) {
    sc.nextLine();
    view.deleteComputer();
    
    String name = null;
    name = sc.nextLine();

    Computer computer = new Computer();
    computer.setName(name);
    serviceComputer.delete(computer);
    view.deletedComputer(name);

    logger.debug("Deleting computer named" + name);
    mainMenu();
  }

  /**
   * Delete company.
   */
  public void deleteCompany(Scanner sc) {
    sc.nextLine();
    view.startDeleteCompany();
    
    int companyId = sc.nextInt();
    Company company = new Company();
    company.setId(companyId);
    logger.debug("Deleting company of id: " + companyId);
    serviceCompany.delete(company);
    view.deleteCompany(company);
    sc.nextLine();
    mainMenu();
  }

  /**
   * Sets the computer name.
   *
   * @return the string
   * @throws ComputerValidationException the computer validation exception
   */
  public String setComputerName(Scanner sc) throws ComputerValidationException {
    view.setComputerName();
    String name = sc.nextLine();

    try {
      computerValidator.validateName(name);
      logger.debug("Setting computer name: " + name);
      return name;
    } catch (ComputerValidationException e) {
      throw e;
    }
  }

  /**
   * Sets the computer intro.
   *
   * @return the date
   * @throws ComputerValidationException the computer validation exception
   */
  public Date setComputerIntro(Scanner sc) throws ComputerValidationException {
    view.addComputerDateIntro();
    Date intro = null;
    String timestamp = null;
    timestamp = sc.nextLine();

    try {
      computerValidator.validateDateFormatIntro(timestamp);
    } catch (ComputerValidationException e) {
      throw e;
    }
    if (!timestamp.equals("")) {
      intro = setDate(timestamp);
    }

    logger.debug("Setting computer date of introduction: " + intro);
    return intro;
  }

  /**
   * Sets the computer disc.
   *
   * @return the date
   * @throws ComputerValidationException the computer validation exception
   */
  public Date setComputerDisc(Scanner sc) throws ComputerValidationException {
    view.addComputerDateDisc();
    Date discontinuation = null;
    String timestamp = null;
    timestamp = sc.nextLine();

    try {
      computerValidator.validateDateFormatDisc(timestamp);
    } catch (ComputerValidationException e) {
      throw e;
    }
    if (!timestamp.equals("")) {
      discontinuation = setDate(timestamp);
    }

    logger.debug("Setting computer date of discontinuation: " + discontinuation);
    return discontinuation;

  }

  /**
   * Sets the computer company.
   *
   * @return the company
   */
  public Company setComputerCompany(Scanner sc) throws ComputerValidationException {
    view.setComputerCompanyId();
    Company company = new Company();
    company.setName(sc.nextLine().trim());
    logger.debug("Setting company Id: " + company.getName());
    List<Company> list = serviceCompany.getCompany(company);

    try {
      company = list.get(0);
    } catch (IndexOutOfBoundsException e) {
      throw new ComputerValidationException("The company name you entered doesn't exist");
    }
    return company;
  }

  /**
   * Adds the computer.
   */
  public void addComputer(Scanner sc) {
    sc.nextLine();
    view.startAddComputer();
    
    Computer computer = setComputer(sc);
    logger.debug("Adding computer: " + computer);
    serviceComputer.add(computer);
    view.addComputer(computer);
    mainMenu();
  }

  /**
   * Update computer.
   */
  public void updateComputer(Scanner sc) {
    sc.nextLine();
    view.startUpdateComputer();
    
    Computer computer = setComputer(sc);
    logger.debug("Updating computer: " + computer);
    serviceComputer.update(computer);
    view.updateComputer(computer);
    mainMenu();
  }

  /**
   * Sets the computer.
   *
   * @return the computer
   */
  public Computer setComputer(Scanner sc) {
    String name = null;
    Date intro = null;
    Date discontinuation = null;
    Company company = new Company();
    try {
      name = setComputerName(sc);
      intro = setComputerIntro(sc);
      discontinuation = setComputerDisc(sc);
      company = setComputerCompany(sc);
    } catch (ComputerValidationException e) {
      logger.error(e.getMessage(), e);
      view.invalidComputer(e.getMessage());
      mainMenu();
    }
    return new Computer(name, company, intro, discontinuation, 0);
  }

  /**
   * Sets the date.
   *
   * @param timestamp the timestamp
   * @return the date
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
