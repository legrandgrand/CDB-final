package dao;

import static org.junit.Assert.assertTrue;

import config.SpringConfigTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import model.Company;
import model.Computer;
import model.Page;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComputerDaoImpTest {
  private static final String DATE_1 = "1997-10-02 00:00:00";
  private static final String DATE_2 = "1997-10-03 00:00:00";
  private static final SimpleDateFormat DT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

  private static ComputerDaoImp computerDaoImp;

  private static ApplicationContext applicationContext;

  /**
   * Sets the up before class.
   *
   * @throws Exception the exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    applicationContext = new AnnotationConfigApplicationContext(SpringConfigTest.class);
    computerDaoImp = applicationContext.getBean("computerDaoImp", ComputerDaoImp.class);
  }

  @AfterClass
  public static void setUpAfterClass() throws Exception {
    ((ConfigurableApplicationContext) applicationContext).close();
  }

  @Test
  public void testGetMaxId() {
    List<Computer> computers = computerDaoImp.list();
    Long id = computerDaoImp.getMaxId();
    System.out.println(computers.size() + " et " + id);
    assertTrue(id == computers.size() - 1);
  }

  /**
   * Test list.
   */
  @Test
  public void testList() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
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
    List<Computer> computers = computerDaoImp.getComputer((int) computer.getId());
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
    Page page = new Page();
    page.setLimit(10); page.setOffset(1);
    List<Computer> computers = computerDaoImp.listPage(page);
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
    Computer computer = new Computer("TestComputer", company, null, null, 575);

    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAddNoDate() throws ParseException {
    Date date1 = DT.parse(DATE_1);
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer2", company, date1, null, 576);
    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAddAll() throws ParseException {
    Date date1 = DT.parse(DATE_1);
    Date date2 = DT.parse(DATE_2);
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer3", company, date1, date2, 577);

    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAddNoIntro() throws ParseException {
    Date date2 = DT.parse(DATE_2);
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer4", company, null, date2, 578);

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
    Computer computer = new Computer("testComputer5", company, null, null, 579);

    computerDaoImp.delete(computer);

    List<Computer> computers = computerDaoImp.list();

    assertTrue(!computers.contains(computer));
  }

}
