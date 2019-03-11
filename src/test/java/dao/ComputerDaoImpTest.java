package dao;

import static org.junit.Assert.assertTrue;

import config.SpringConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import model.Company;
import model.Computer;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComputerDaoImpTest {
  private static final String DATE_1 = "1997-10-02 00:00:00";
  private static final String DATE_2 = "1997-10-03 00:00:00";
  private static final SimpleDateFormat DT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

  @Autowired
  private ComputerDaoImp computerDaoImp;
  
//  @ClassRule
//  public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
//
//  @Rule
//  public final SpringMethodRule springMethodRule = new SpringMethodRule();


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
  public void testUpdate() throws ParseException {
    Company company = new Company("Apple Inc.", 1);
    // Case 1: have company number, no dates
    Computer computer = new Computer("MacBook Pro 15.4 inch", company, null, null, 1);
    computerDaoImp.update(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 2: have company number, date of intro
    Date date1 = DT.parse(DATE_1);

    computer = new Computer("MacBook Pro 15.4 inch", company, date1, null, 1);
    computerDaoImp.update(computer);
    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 3: have company number, date of intro and disc superior to date of intro
    Date date2 = DT.parse(DATE_2);
    computer = new Computer("MacBook Pro 15.4 inch", company, date1, date2, 1);
    computerDaoImp.update(computer);

    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  /**
   * Test add.
   *
   * @throws ParseException the parse exception
   */
  @Test
  public void testAdd() throws ParseException {
    Date date1 = DT.parse(DATE_1);
    // Case 1: have company number, no dates
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("TestComputer", company, null, null, 580);

    computerDaoImp.add(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 2: have company number, date of intro
    computer = new Computer("TestComputer2", company, date1, null, 580);
    computerDaoImp.add(computer);

    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 3: have company number, date of intro and disc superior to date of intro
    Date date2 = DT.parse(DATE_2);
    computer = new Computer("TestComputer3", company, date1, date2, 580);

    computerDaoImp.add(computer);

    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 4: have company number, date of disc
    computer = new Computer("TestComputer3", company, null, date2, 580);

    computerDaoImp.add(computer);

    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  /**
   * Test delete.
   */
  @Test
  public void testDelete() {
    // Case 1: successfull delete

    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("testComputer", company, null, null, 580);

    computerDaoImp.delete(computer);

    List<Computer> computers = computerDaoImp.list();

    assertTrue(!computers.contains(computer));
  }

}
