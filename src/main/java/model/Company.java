package model;

/**
 * The Class Company.
 */
public class Company {
	
	private String nameCompany;

	public Company(String nameCompany) {
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
