package model;

import java.sql.Timestamp;

/**
 * The Class Computer.
 */
public class Computer {
	
	/** The PC name. */
	private String namePC;//Compulsory
	
	/** The name of the manufacturer. */
	private int nameManufacturer;
	
	/** The date of introduction. */
	private Timestamp dateIntro;
	
	/** The date of discontinuation. */
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
		super();
		this.setNamePC(namePC);
		this.setNameManuf(nameManufacturer);
		this.setDateIntro(dateIntro);
		this.setDateDiscontinuation(dateDiscontinuation);
	}

	/**
	 * Instantiates a new computer.
	 */
	public Computer() {
	}

	public Timestamp getDateB() {
		return dateIntro;
	}

	public void setDateIntro(Timestamp dateB) {
		this.dateIntro = dateB;
	}

	public Timestamp getDateF() {
		return dateDiscontinuation;
	}

	public void setDateDiscontinuation(Timestamp dateF) {
		this.dateDiscontinuation = dateF;
	}

	public int getNameManuf() {
		return nameManufacturer;
	}

	public void setNameManuf(int nameManuf) {
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
