package dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Computer;

public class ComputerDaoImpTest {
	private ComputerDaoImp computerDaoImp;
	String date_s1 = "1997-10-02 00:00:00"; 
    String date_s2 = "1997-10-03 00:00:00"; 
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	
	
	@Before
	public void setUp() throws Exception {
		computerDaoImp = ComputerDaoImp.getInstance();
	}
	

	@Test
	public void testList() {
		Computer computer = new Computer ("Lenovo Thinkpad Edge 11", 36, null, null);
		List<Computer> computers = computerDaoImp.list();
	    assertEquals(true, computers.contains(computer));		
	}



	@Test
	public void testUpdate() throws ParseException {

        Date date1 = dt.parse(date_s1);
        Date date2 = dt.parse(date_s2);
		//Case 1: have company number, no dates
		Computer computer = new Computer("MacBook Pro 15.4 inch", 1, null, null);
		
		computerDaoImp.update(computer);
         
        List<Computer> computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
        
        //Case 2: have company number, date of intro
        computer = new Computer("MacBook Pro 15.4 inch", 1, date1, null);
		computerDaoImp.update(computer);
         
        computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
        
        //Case 3: have company number, date of intro and disc superior to date of intro
        computer = new Computer("MacBook Pro 15.4 inch", 1, date1, date2);
		
		computerDaoImp.update(computer);
         
        computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
	}

	@Test
	public void testAdd() throws ParseException {
		Date date1 = dt.parse(date_s1);
        Date date2 = dt.parse(date_s2);
		//Case 1: have company number, no dates
		Computer computer = new Computer("TestComputer", 1, null, null);
		
		computerDaoImp.add(computer);
         
        List<Computer> computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
        
        //Case 2: have company number, date of intro
        computer = new Computer("TestComputer2", 1, date1, null);
		computerDaoImp.add(computer);
         
        computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
        
        //Case 3: have company number, date of intro and disc superior to date of intro
        computer = new Computer("TestComputer3", 1, date1, date2);
		
		computerDaoImp.add(computer);
         
        computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
        
      //Case 4: have company number, date of disc
        computer = new Computer("TestComputer3", 1, null, date2);
		
		computerDaoImp.add(computer);
         
        computers = computerDaoImp.list();
        assertEquals(true, computers.contains(computer));
	}
	
	@Test
	public void testDelete() {
		//Case 1: successfull delete
		Computer computer = new Computer("testComputer", 1, null, null);
		String computerName = computer.getName();
		
		computerDaoImp.delete(computerName);
         
        List<Computer> computers = computerDaoImp.list();
        assertEquals(true, !computers.contains(computer));
	}

}
