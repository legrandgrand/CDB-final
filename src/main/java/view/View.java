package view;

import controller.Controller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;

public class View {
  private Controller controller;

  /**
   * Instantiates a new view.
   *
   * @param controller the controller
   */
  public View(Controller controller) {
    this.controller = controller;
    System.out.print("Welcome to the computer database program.");
    mainMenu();
  }

  /**
   * Main menu.
   */
  public void mainMenu() {
    System.out.println("What would you like to do?"
        + "\n1. List Manufacturer companies."
        + "\n2. List Computer database."
        + "\n3. Add a Computer to the database."
        + "\n4. Delete a Computer from the database."
        + "\n5. Delete a Company (and associated Computers) from the database."
        + "\n6. Update a Computer in the database."
        + "\n7. Exit program.");
    Scanner sc = new Scanner(System.in);
    try {
      int userChoice = sc.nextInt();
      switch (userChoice) { // TODO: enums
        case 1:
          listCompanies();
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
   * List companies.
   */
  public void listCompanies() {
    System.out.println("We will now list all companies of manufacturers.");
    List<Company> list = controller.listCompany();
    Company company = new Company();
    Iterator<Company> itr = list.iterator();
    while (itr.hasNext()) {
      company = (Company) itr.next();
      System.out.println(company);
    }
    mainMenu();
  }

  /**
   * List computers.
   */
  public void listComputers() {
    System.out.println("We will now list all computers of the database.");
    List<Computer> list = controller.listComputer();
    Computer computer = new Computer();
    Iterator<Computer> itr = list.iterator();
    while (itr.hasNext()) {
      computer = (Computer) itr.next();
      System.out.println(computer);
    }
    mainMenu();
  }

  /**
   * Adds the computer.
   */
  public void addComputer() {
    Scanner sc = new Scanner(System.in);
    System.out.println("We will now add a new computer.");
    try {
      String name = setComputerName(sc);

      Date intro = addComputerDateIntro(sc);

      Date discontinuation = addComputerDateDisc(sc, intro);

      Company companyId = setComputerCompanyId(sc);

      Computer computer = controller.addComputer(name, intro, discontinuation, companyId);

      System.out.println(computer + " a été rajouté");
      mainMenu();
    } catch (IllegalArgumentException e) {
      System.out
          .println("The argument you entered doesn't have the correct format. Please try again.");

      addComputer();
    } catch (InputMismatchException e) {
      System.out.println("Not a valid input. Please try again.");
      addComputer();

    } catch (Exception e) {
      System.out.println(e);
      addComputer();
    }
  }

  /**
   * Adds the computer date intro.
   *
   * @param sc the sc
   * @return the date
   * @throws Exception the exception
   */
  public Date addComputerDateIntro(Scanner sc) throws Exception {
    System.out.println("Please enter the date of introduction. "
        + "Format should be YYYY-MM-DD. (If the date is unknown, just press enter.)");
    return controller.setComputerIntro(sc);
  }

  /**
   * Sets the computer name.
   *
   * @param sc the sc
   * @return the string
   * @throws Exception exception
   */
  public String setComputerName(Scanner sc) throws Exception {
    System.out.println("Please enter the computer's name you want to add or update");
    return controller.setComputerName(sc);
  }

  /**
   * Adds the computer date disc.
   *
   * @param sc    the sc
   * @param intro the intro
   * @return the date
   * @throws Exception the exception
   */
  public Date addComputerDateDisc(Scanner sc, Date intro) throws Exception {
    System.out
        .println("Please enter the date of discontinuation. " + "Format should be YYYY-MM-DD. "
            + "(If the date is unknown or the computer is still continuing, just press enter.)");
    return controller.setComputerDisc(sc, intro);
  }

  /**
   * Sets the computer company id.
   *
   * @param sc the sc
   * @return the int
   */
  public Company setComputerCompanyId(Scanner sc) {
    System.out.println("Please enter the company's name");
    return controller.setComputerCompany(sc);
  }

  /**
   * Delete computer.
   */
  public void deleteComputer() {
    System.out.println("We will now delete a computer from the database."
        + "Please enter the computer's name you want to delete.");
    try (Scanner sc = new Scanner(System.in)) {
      String computerName = sc.nextLine();
      controller.deleteComputer(computerName);
      System.out.println(computerName + "a été supprimé");
      mainMenu();
    }

  }
  
  /**
   * Delete company.
   */
  public void deleteCompany() {
    System.out.println("We will now delete a company (and associated Computers) from the database."
        + "Please enter the company's id you want to delete.");
    try (Scanner sc = new Scanner(System.in)) {
      int companyId = sc.nextInt();
      controller.deleteCompany(companyId);
      System.out.println(companyId + "a été supprimé");
      sc.nextLine();
      mainMenu();
    }

  }

  /**
   * Update computer.
   */
  public void updateComputer() {

    System.out.println("We will now update a computer from the database.");
    Scanner sc = new Scanner(System.in);
    try {
      String name = setComputerName(sc);

      Date intro = addComputerDateIntro(sc);

      Date discontinuation = addComputerDateDisc(sc, intro);

      Company companyId = setComputerCompanyId(sc);

      Computer computer = controller.updateComputer(name, intro, discontinuation, companyId);
      System.out.println(computer + " was updated");

      mainMenu();
    } catch (IllegalArgumentException e) {
      System.out
          .println("The argument you entered doesn't have the correct format. Please try again.");
      updateComputer();
    } catch (InputMismatchException e) {
      System.out.println("Not a valid input. Please try again.");
      updateComputer();
    } catch (Exception e) {
      System.out.println(e);
      updateComputer();
    }
  }

}
