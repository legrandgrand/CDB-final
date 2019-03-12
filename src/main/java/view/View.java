package view;

import java.util.Iterator;
import java.util.List;

import model.Company;
import model.Computer;

import org.springframework.stereotype.Component;

@Component
public class View {

  /**
   * Instantiates a new view.
   */
  private View() {
  }

  /**
   * Start.
   */
  public void start() {
    System.out.print("Welcome to the computer database program.");
    mainMenu();
  }

  /**
   * Main menu.
   */
  public void mainMenu() {
    System.out.println("What would you like to do?" + "\n1. List Manufacturer companies."
        + "\n2. List Computer database." + "\n3. Add a Computer to the database."
        + "\n4. Delete a Computer from the database."
        + "\n5. Delete a Company (and associated Computers) from the database."
        + "\n6. Update a Computer in the database." + "\n7. Exit program.");
  }

  /**
   * List companies.
   */
  public void startListCompanies() {
    System.out.println("We will now list all companies of manufacturers.");
  }

  /**
   * List companies.
   *
   * @param list the list
   */
  public void listCompanies(List<Company> list) {
    Company company = new Company();
    Iterator<Company> itr = list.iterator();
    while (itr.hasNext()) {
      company = (Company) itr.next();
      System.out.println(company);
    }
  }

  /**
   * List computers.
   */
  public void startListComputers() {
    System.out.println("We will now list all computers of the database.");
  }

  /**
   * List computers.
   *
   * @param list the list
   */
  public void listComputers(List<Computer> list) {
    Computer computer = new Computer();
    Iterator<Computer> itr = list.iterator();
    while (itr.hasNext()) {
      computer = (Computer) itr.next();
      System.out.println(computer);
    }
  }

  /**
   * Adds the computer.
   *
   * @param computer the computer
   */
  public void addComputer(Computer computer) {
    System.out.println(computer + " a été rajouté");
  }

  /**
   * Start add computer.
   */
  public void startAddComputer() {
    System.out.println("We will now add a new computer.");
  }

  /**
   * Sets the computer name.
   */
  public void setComputerName() {
    System.out.println("Please enter the computer's name you want to add or update");
  }

  /**
   * Adds the computer date disc.
   */
  public void addComputerDateDisc() {
    System.out
        .println("Please enter the date of discontinuation. " + "Format should be YYYY-MM-DD. "
            + "(If the date is unknown or the computer is still continuing, just press enter.)");
  }

  /**
   * Adds the computer date intro.
   */
  public void addComputerDateIntro() {
    System.out.println("Please enter the date of introduction. "
        + "Format should be YYYY-MM-DD. (If the date is unknown, just press enter.)");
  }

  /**
   * Sets the computer company id.
   */
  public void setComputerCompanyId() {
    System.out.println("Please enter the company's name");
  }

  /**
   * Delete computer.
   */
  public void deleteComputer() {
    System.out.println("We will now delete a computer from the database."
        + "Please enter the computer's name you want to delete.");
  }

  /**
   * Delete company.
   */
  public void startDeleteCompany() {
    System.out.println("We will now delete a company (and associated Computers) from the database."
        + "Please enter the company's id you want to delete.");
  }

  /**
   * Delete company.
   *
   * @param company the company
   */
  public void deleteCompany(Company company) {
    System.out.println(company.getName() + "a été supprimé");
  }

  /**
   * Update computer.
   *
   * @param computer the computer
   */
  public void updateComputer(Computer computer) {
    System.out.println(computer + " was updated");
  }

  /**
   * Start update computer.
   */
  public void startUpdateComputer() {
    System.out.println("We will now update a computer from the database.");
  }

  /**
   * Deleted computer.
   *
   * @param name the name
   */
  public void deletedComputer(String name) {
    System.out.println(name + "a été supprimé");
  }

}
