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
	public Company() {}

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (nameCompany == null) {
			if (other.nameCompany != null)
				return false;
		} else if (!nameCompany.equals(other.nameCompany))
			return false;
		return true;
	}
	
	
}
