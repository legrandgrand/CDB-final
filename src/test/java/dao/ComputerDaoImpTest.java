package dao;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import model.Company;
import model.Computer;

import org.junit.Before;
import org.junit.Test;

public class ComputerDaoImpTest {
  private static final String DATE_1 = "1997-10-02 00:00:00";
  private static final String DATE_2 = "1997-10-03 00:00:00";
  private ComputerDaoImp computerDaoImp;
  SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

  @Before
  public void setUp() throws Exception {
    computerDaoImp = ComputerDaoImp.getInstance();
  }

  @Test
  public void testList() {
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("Lenovo Thinkpad Edge 11", company, null, null, 571);
    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testGetComputer() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
    List<Computer> computers = computerDaoImp.getComputer(2);
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testGetComputerFromName() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
    List<Computer> computers = computerDaoImp.getComputerFromName("CM-2a");
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testListPage() {
    Company company = new Company("Thinking Machines", 2);
    Computer computer = new Computer("CM-2a", company, null, null, 2);
    List<Computer> computers = computerDaoImp.listPage(1);
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testUpdate() throws ParseException {

    Company company = new Company("Apple Inc.", 1);
    // Case 1: have company number, no dates
    Computer computer = new Computer("MacBook Pro 15.4 inch", company, null, null, 1);
    computerDaoImp.update(computer);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 2: have company number, date of intro
    Date date1 = dt.parse(DATE_1);

    computer = new Computer("MacBook Pro 15.4 inch", company, date1, null, 1);
    computerDaoImp.update(computer);
    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));

    // Case 3: have company number, date of intro and disc superior to date of intro
    Date date2 = dt.parse(DATE_2);
    computer = new Computer("MacBook Pro 15.4 inch", company, date1, date2, 1);
    computerDaoImp.update(computer);

    computers = computerDaoImp.list();
    assertTrue(computers.contains(computer));
  }

  @Test
  public void testAdd() throws ParseException {
    Date date1 = dt.parse(DATE_1);
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
    Date date2 = dt.parse(DATE_2);
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

  @Test
  public void testDelete() {
    // Case 1: successfull delete
    Company company = new Company("Apple Inc.", 1);
    Computer computer = new Computer("testComputer", company, null, null, 580);
    String computerName = computer.getName();

    computerDaoImp.delete(computerName);

    List<Computer> computers = computerDaoImp.list();
    assertTrue(!computers.contains(computer));
  }

}
