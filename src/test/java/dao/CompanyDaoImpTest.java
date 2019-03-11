package dao;

import static org.junit.Assert.assertEquals;

import config.SpringConfig;

import java.util.List;

import model.Company;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CompanyDaoImpTest {
  @Autowired
  private CompanyDaoImp companyDaoImp;

  @Autowired
  ApplicationContext context;

  /**
   * Sets the up before class.
   *
   * @throws Exception the exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

    ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(SpringConfig.class);
  }

  /**
   * Test list.
   */
  @Test
  public void testList() {
    Company company = new Company("OQO", 18);
    List<Company> companies = companyDaoImp.list();
    assertEquals(true, companies.contains(company));
  }

  /**
   * Test get company.
   */
  @Test
  public void testGetCompany() {
    Company company = new Company("OQO", 18);
    List<Company> companies = companyDaoImp.getCompany(company);
    assertEquals(true, companies.contains(company));
  }
}
