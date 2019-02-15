package model;

/**
 * The Class Company.
 */
public class Company {
	
	/** The company name. */
	private String nameCompany;



	/**
	 * Instantiates a new company.
	 *
	 * @param nameCompany the company name
	 */
	public Company(String nameCompany) {
		super();
		this.setNameCompany(nameCompany);
	}

	/**
	 * Instantiates a new company.
	 */
	public Company() {
		// TODO Auto-generated constructor stub
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company:" + nameCompany;
	}
}
