package dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import model.Company;

import org.junit.Before;
import org.junit.Test;

public class CompanyDaoImpTest {
  private CompanyDaoImp companyDaoImp;

  @Before
  public void setUp() throws Exception {
    companyDaoImp = CompanyDaoImp.getInstance();
  }

  @Test
  public void testList() {
    Company company = new Company("OQO",18);
    List<Company> companies = companyDaoImp.list();
    assertEquals(true, companies.contains(company));
  }
  
  @Test
  public void testGetCompany() {
    Company company = new Company("OQO",18);
    List<Company> companies = companyDaoImp.getCompany(company);
    assertEquals(true, companies.contains(company));
  }
}
