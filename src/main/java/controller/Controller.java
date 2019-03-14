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
public class Controller {

  private static final Logger logger = LoggerFactory.getLogger(Controller.class);

  private View view;
  private ServiceComputer serviceComputer;
  private ServiceCompany serviceCompany;
  private ComputerValidator computerValidator;

  @Autowired
  public Controller(ComputerValidator computerValidator, ServiceCompany serviceCompany, ServiceComputer serviceComputer, View view) {
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
          addComputer();
          break;
        case 4:
          deleteComputer();
          break;
        case 5:
          deleteCompany();
          break;
        case 6:
          updateComputer();
          break;
        case 7:
          System.out.println("Exiting program");
          sc.close();
          System.exit(0);
          break;
        default:
          System.out.println("Not a valid statement.");
          mainMenu();
          break;
      }
    } catch (InputMismatchException e) {
      System.out.println("Not a valid input.");

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
   * List computer.
   *
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
  public void deleteComputer() {
    view.deleteComputer();
    String name = null;
    Scanner sc = new Scanner(System.in);
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
  public void deleteCompany() {
    view.startDeleteCompany();
    try (Scanner sc = new Scanner(System.in)) {
      int companyId = sc.nextInt();
      Company company = new Company();
      company.setId(companyId);
      logger.debug("Deleting company of id: " + companyId);
      serviceCompany.delete(company);
      view.deleteCompany(company);
      sc.nextLine();
      mainMenu();
    }
  }

  /**
   * Sets the computer name.
   *
   * @return the ComputerName
   * @throws ComputerValidationException the computer validation exception
   */
  public String setComputerName() throws ComputerValidationException {
    view.setComputerName();
    Scanner sc = new Scanner(System.in);
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
   * @return the timestamp
   * @throws ComputerValidationException the computer validation exception
   */
  public Date setComputerIntro() throws ComputerValidationException {
    view.addComputerDateIntro();
    Scanner sc = new Scanner(System.in);
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
   * @return the date of discontinuation
   * @throws ComputerValidationException the computer validation exception
   */
  public Date setComputerDisc() throws ComputerValidationException {
    view.addComputerDateDisc();
    Date discontinuation = null;
    String timestamp = null;
    Scanner sc = new Scanner(System.in);
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
   * Sets the computer company id.
   *
   * @return the Company Id
   */
  public Company setComputerCompany() {
    view.setComputerCompanyId();
    Company company = new Company();
    Scanner sc = new Scanner(System.in);
    company.setName(sc.nextLine());
    logger.debug("Setting company Id: " + company.getName());
    company = serviceCompany.getCompany(company).get(0);
    return company;
  }

  /**
   * Adds the computer.
   *
   */
  public void addComputer() {
    view.startAddComputer();
    Computer computer = setComputer();
    logger.debug("Adding computer: " + computer);
    serviceComputer.add(computer);
    view.addComputer(computer);
    mainMenu();
  }


  /**
   * Update computer.
   *
   */
  public void updateComputer() {
    view.startUpdateComputer();
    Computer computer = setComputer();
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
  public Computer setComputer() {
    String name = null;
    Date intro = null;
    Date discontinuation = null;
    Company company = new Company();
    try {
      name = setComputerName();
      intro = setComputerIntro();
      discontinuation = setComputerDisc();
      company = setComputerCompany();
    } catch (ComputerValidationException e) {
      logger.error(e.getMessage(), e);
      mainMenu();
    } catch (IllegalArgumentException e) {
      System.out
          .println("The argument you entered doesn't have the correct format. Please try again.");
      mainMenu();
    } catch (InputMismatchException e) {
      System.out.println("Not a valid input. Please try again.");
      mainMenu();
    }
    return new Computer(name, company, intro, discontinuation, 0);
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
