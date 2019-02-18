package model;

import java.util.Date;

public class Computer {
	
	private String namePC;//Compulsory
	private int nameManufacturer;//TODO: pas entier, mais company
	private Date dateIntro;
	private Date dateDiscontinuation;//Has to be higher than date B
	
	/**
	 * Instantiates a new computer.
	 *
	 * @param namePC the name PC
	 * @param nameManufacturer the name manufacturer
	 * @param dateIntro the date intro
	 * @param dateDiscontinuation the date discontinuation
	 */
	public Computer(String namePC, int nameManufacturer, Date dateIntro, Date dateDiscontinuation) {
		this.setNamePC(namePC);
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
		return namePC;
	}

	public void setNamePC(String namePC) {
		this.namePC = namePC;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Name: "+ namePC + "\n Introduction : " +  dateIntro + "\n Discontinuation: " + dateDiscontinuation + "\n Company Number: "+ nameManufacturer;
	}

}
