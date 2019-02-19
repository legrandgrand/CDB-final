package dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Company;

public class CompanyDaoImpTest {
	private CompanyDaoImp companyDaoImp;
	
	@Before
	public void setUp() throws Exception {
		companyDaoImp = CompanyDaoImp.getInstance();
	}
	

	@Test
	public void testList() {
		Company company = new Company ("OQO");
		List<Company> companies = companyDaoImp.list();
	    assertEquals(true, companies.contains(company));		
	}
}
