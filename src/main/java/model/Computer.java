package model;

import java.sql.Timestamp;

public class Computer {
	
	private String namePC;//Compulsory
	private int nameManufacturer;//TODO: pas entier, mais company
	private Timestamp dateIntro;//TODO: Pas timestamp, date
	private Timestamp dateDiscontinuation;//Has to be higher than date B
	
	/**
	 * Instantiates a new computer.
	 *
	 * @param namePC the name PC
	 * @param nameManufacturer the name manufacturer
	 * @param dateIntro the date intro
	 * @param dateDiscontinuation the date discontinuation
	 */
	public Computer(String namePC, int nameManufacturer, Timestamp dateIntro, Timestamp dateDiscontinuation) {
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

	public Timestamp getDateIntro() {
		return dateIntro;
	}

	public void setDateIntro(Timestamp dateB) {
		this.dateIntro = dateB;
	}

	public Timestamp getDateDiscontinuation() {
		return dateDiscontinuation;
	}

	public void setDateDiscontinuation(Timestamp dateF) {
		this.dateDiscontinuation = dateF;
	}

	public int getNameManufacturer() {
		return nameManufacturer;
	}

	public void setNameManufacturer(int nameManuf) {
		this.nameManufacturer = nameManuf;
	}

	public String getNamePC() {
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
