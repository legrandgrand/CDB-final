package view;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.Controller;
import model.Computer;

/**
 * The Class View.
 */
public class View {
	
	/** The controller. */
	private Controller controller;
	
	/**
	 * Instantiates a new view.
	 *
	 * @param controller the controller
	 */
	public View(Controller controller) {
		this.controller=controller;
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
				+ "\n5. Update a Computer in the database."
				+ "\n6. Exit program."); 
		Scanner sc = new Scanner(System.in);
		try {
			int userChoice = sc.nextInt();
			System.out.println("Choix user: "+ userChoice);
			switch (userChoice) {
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
				updateComputer();
				break;
			case 6:
				sc.close();
				System.exit(0);
			default:
				System.out.println("Not a valid statement.");
				mainMenu();
				break;
			}	
		} catch ( InputMismatchException e ) {
			System.out.println("Not a valid input.");
			
			mainMenu();
		}
	
	}

		
	/**
	 * List companies.
	 */
	public void listCompanies() {
		System.out.println("We will now list all companies of manufacturers.");
		 controller.listCompany();
		 mainMenu();
	}
	
	/**
	 * List computers.
	 */
	public void listComputers() {
		System.out.println("We will now list all computers of the database.");
		controller.listComputer();
		mainMenu();
	}
	
	
	/**
	 * Adds the computer.
	 */
	public void addComputer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("We will now add a new computer.");	
		try {
			String name= setComputerName(sc);
			
			Date intro = addComputerDateIntro(sc);
			
			Date discontinuation = addComputerDateDisc(sc, intro);
			
			int companyId = setComputerCompanyId(sc);		
			
			Computer computer = controller.addComputer(name, intro, discontinuation, companyId);
			
			System.out.println(computer + " a été rajouté");
			mainMenu();
		} catch (IllegalArgumentException e) {
			System.out.println("The argument you entered doesn't have the correct format. Please try again.");
	
			addComputer();
		} catch ( InputMismatchException e ) {
			System.out.println("Not a valid input. Please try again.");
			addComputer();
				
		}
	}
	
	public Date addComputerDateIntro(Scanner sc) {
		System.out.println("Please enter the date of introduction. Format should be YYYY-MM-DD. (If the date is unknown, just press enter.)");
		return controller.setComputerIntro(sc);
	}
	
	public String setComputerName(Scanner sc) {
		System.out.println("Please enter the computer's name you want to add or update");
		return controller.setComputerName(sc);
	}
	
	public Date addComputerDateDisc(Scanner sc, Date intro) {
		System.out.println("Please enter the date of discontinuation. Format should be YYYY-MM-DD. (If the date is unknown or the computer is still continuing, just press enter.)");		
		return controller.setComputerDisc(sc,intro);
	}
	
	public int setComputerCompanyId(Scanner sc) {
		System.out.println("Please enter the company's number");
		return controller.setComputerCompanyId(sc);
	}

	
	/**
	 * Delete computer.
	 */
	public void deleteComputer() {
		System.out.println("We will now delete a computer from the database."
				+ "Please enter the computer's name you want to delete.");
		Scanner sc = new Scanner(System.in);
		String computerName=sc.nextLine();
		controller.deleteComputer(computerName);	
		System.out.println(computerName + "a été supprimé");
		mainMenu();
	}

	/**
	 * Update computer.
	 */
	public void updateComputer() {
		
		System.out.println("We will now update a computer from the database.");
		Scanner sc = new Scanner(System.in);
		try{
			String name= setComputerName(sc);
			
			Date intro = addComputerDateIntro(sc);
			
			Date discontinuation = addComputerDateDisc(sc, intro);
			
			int companyId = setComputerCompanyId(sc);		
			
			Computer computer = controller.updateComputer(name, intro, discontinuation, companyId);
			System.out.println(computer + " was updated");
			
			mainMenu();
			} catch (IllegalArgumentException e) {
				System.out.println("The argument you entered doesn't have the correct format. Please try again.");
				updateComputer();
			}catch ( InputMismatchException e ) {
				System.out.println("Not a valid input. Please try again.");
				updateComputer();
		}
	}


	
	

}
