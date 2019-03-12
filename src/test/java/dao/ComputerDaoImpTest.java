package dao;

import static org.junit.Assert.assertTrue;

import com.zaxxer.hikari.HikariDataSource;

import config.SpringConfigTest;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import model.Company;
import model.Computer;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComputerDaoImpTest {
  private static final String DATE_1 = "1997-10-02 00:00:00";
  private static final String DATE_2 = "1997-10-03 00:00:00";
  private static final SimpleDateFormat DT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

  private static ComputerDaoImp computerDaoImp;

  private static HikariDataSource dataSource;

  /**
   * Sets the up before class.
   *
   * @throws Exception the exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SpringConfigTest.class);
    dataSource = applicationContext.getBean("dataSource", HikariDataSource.class);
    computerDaoImp = applicationContext.getBean("computerDaoImp", ComputerDaoImp.class);
    Connection connection = dataSource.getConnection();
  }
  
  @Test
  public void testGetMaxId() {
    int maxId = 574;
    assertTrue(computerDaoImp.getMaxId() == maxId);
  }

  /**
   * Test list.
   */
  @Test
  public void testList() {
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("Lenovo Thinkpad Edge 11", company, null, null, 571);
    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  /**
   * Test get computer.
   */
  @Test
  public void testGetComputer() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
    List<Computer> computers = computerDaoImp.getComputer(computer);
    assertTrue(computers.contains(computer));
  }

  /**
   * Test get computer from name.
   */
  @Test
  public void testGetComputerFromName() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
    List<Computer> computers = computerDaoImp.getComputerFromName(computer);
    assertTrue(computers.contains(computer));
  }

  /**
   * Test list page.
   */
  @Test
  public void testListPage() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
    List<Computer> computers = computerDaoImp.listPage(10, 1);
    assertTrue(computers.contains(computer));
  }

  /**
   * Test update.
   *
   * @throws ParseException the parse exception
   */
  @Test
  public void testUpdateNoDate() throws ParseException {
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("MacBook Pro 15.4 inch", company, null, null, 1);
    computerDaoImp.update(computer);
    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testUpdateNoDisc() throws ParseException {
    Company company = new Company("Apple Inc.", 1);
    Date date1 = DT.parse(DATE_1);
    Computer computer = new Computer("MacBook Pro 15.4 inch", company, date1, null, 1);
    computerDaoImp.update(computer);
    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testUpdateAll() throws ParseException {
    Company company = new Company("Apple Inc.", 1);
    Date date1 = DT.parse(DATE_1);
    Date date2 = DT.parse(DATE_2);
    Computer computer = new Computer("MacBook Pro 15.4 inch", company, date1, date2, 1);
    computerDaoImp.update(computer);
    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  /**
   * Test add.
   *
   * @throws ParseException the parse exception
   */
  @Test
  public void testAdd() throws ParseException {
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer", company, null, null, 580);

    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAddNoDate() throws ParseException {
    Date date1 = DT.parse(DATE_1);
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer2", company, date1, null, 580);
    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAddAll() throws ParseException {
    Date date1 = DT.parse(DATE_1);
    Date date2 = DT.parse(DATE_2);
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer3", company, date1, date2, 580);

    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAddNoIntro() throws ParseException {
    Date date2 = DT.parse(DATE_2);
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer3", company, null, date2, 580);

    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  /**
   * Test delete.
   */
  @Test
  public void testDelete() {
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("testComputer", company, null, null, 580);

    computerDaoImp.delete(computer);

    List<Computer> computers = computerDaoImp.list();

    assertTrue(!computers.contains(computer));
  }
  


}
