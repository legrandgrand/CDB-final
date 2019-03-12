package dao;

import static org.junit.Assert.assertEquals;

import config.SpringConfigTest;

import java.sql.Connection;
import java.util.List;

import model.Company;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zaxxer.hikari.HikariDataSource;

public class CompanyDaoImpTest {

  private static CompanyDaoImp companyDaoImp;

  private static HikariDataSource dataSource;

  /**
   * Sets the up before class.
   *
   * @throws Exception the exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(SpringConfigTest.class);
    dataSource = applicationContext.getBean("dataSource", HikariDataSource.class);
    companyDaoImp = applicationContext.getBean("companyDaoImp", CompanyDaoImp.class);
    Connection connection = dataSource.getConnection();
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
  
  @Test
  public void testGetCompanyFromId() {
    Company company = new Company("OQO", 18);
    List<Company> companies = companyDaoImp.getCompanyFromId(company);
    assertEquals(true, companies.contains(company));
  }
}
