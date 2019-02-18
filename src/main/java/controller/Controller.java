package controller;

import java.sql.Date;
import java.util.Iterator;
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
	 * @param serviceComputer the service of the Computer
	 * @param serviceComputer the service of the Company
	 */
	public Controller(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
	}

	

	
	/**
	 * List company.
	 */
	public void listCompany() {
		List<Company> list = serviceCompany.listCompany();
		Company company = new Company();
		 Iterator<Company> itr = list.iterator();
		 while(itr.hasNext()) {
			 company = (Company) itr.next();
			 System.out.println(company);
		 }
	}
	
	/**
	 * List computer.
	 */
	public void listComputer() {
		List<Computer> list = serviceComputer.listComputer();
		Computer computer = new Computer();
		Iterator<Computer> itr = list.iterator();
		while(itr.hasNext()) {
			computer = (Computer) itr.next();
			System.out.println(computer);
		}
		
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
		timestamp=sc.nextLine();
		if(!timestamp.equals("")) {
			intro = setTimestamp(timestamp);
		}
		return intro;
	}
	
	/**
	 * Sets the computer disc.
	 *
	 * @param sc the scanner
	 * @param intro the date of introduction
	 * @return the date of discontinuation
	 */
	public Date setComputerDisc(Scanner sc, Date intro) {//TODO: gérer le cas où intro est null
		Date discontinuation = null;
		String timestamp = null;	
		do {
			timestamp=sc.nextLine();
			if(!timestamp.equals("")) {
				discontinuation = setTimestamp(timestamp);
				/*if(null.equals(intro)) {//TODO: null.equals()null
					break;
				}*/
				if(discontinuation.before(intro)) {
					System.out.println("The date you entered happened before the date of introduction. Please enter a valid date.");
				}
			}
			else {
				break;
			}
		} while(intro.equals(null)||discontinuation.before(intro));
		
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
	 * @param name the name
	 * @param intro the date of introduction
	 * @param discontinuation the date of discontinuation
	 * @param companyId the company id
	 * @return the computer
	 */
	public Computer addComputer(String name, Date intro, Date discontinuation, int companyId) {
		Computer computer=new Computer(name, companyId, intro, discontinuation);
		serviceComputer.addComputer(computer);
		return computer;
	}
	
	/**
	 * Update computer.
	 *
	 * @param name the computer name
	 * @param intro the date of introduction
	 * @param discontinuation the date of discontinuation
	 * @param companyId the company id
	 * @return the computer
	 */
	public Computer updateComputer(String name, Date intro, Date discontinuation, int companyId) {
		Computer computer=new Computer(name, companyId, intro, discontinuation);
		serviceComputer.updateComputer(computer);
		
		return computer;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the timestamp to change
	 * @return the timestamp
	 */
	public Date setTimestamp(String timestamp){	
		timestamp=timestamp+" 00:00:00";//timestamp format: YYYY-MM-DD (user input) + 00:00:00 
		return Date.valueOf(timestamp);
	}

}
