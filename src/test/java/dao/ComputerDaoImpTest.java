package dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Computer;

public class ComputerDaoImpTest {
	private ComputerDaoImp computerDAOImp;

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		//Case 1: successfull delete
		Computer computer = new Computer("testComputer", 1, null, null);
		String computerName = computer.getName();
		
		computerDAOImp.delete(computerName);
         
        List<Computer> computers = computerDAOImp.list();
        Assert.assertNotEquals(computer.getName(), computers.get(0).getName());
	}

	@Test
	public void testUpdate() {
		//Case 1: have company number, no dates
		Computer computer = new Computer("testComputer", 1, null, null);
		
		computerDAOImp.update(computer);
         
        List<Computer> computers = computerDAOImp.list();
        Assert.assertEquals(computer.getName(), computers.get(0).getName());
        
        //Case 2: have company number, date of intro
        computer = new Computer("testComputer", 1, Date.valueOf("1997-10-02 00:00:00"), null);
		
		computerDAOImp.update(computer);
         
        computers = computerDAOImp.list();
        Assert.assertEquals(computer.getName(), computers.get(0).getName());
        
        //Case 3: have company number, date of intro and disc superior to date of intro
        computer = new Computer("testComputer", 1, Date.valueOf("1997-10-02 00:00:00"), Date.valueOf("1997-10-03 00:00:00"));
		
		computerDAOImp.update(computer);
         
        computers = computerDAOImp.list();
        Assert.assertEquals(computer.getName(), computers.get(0).getName());
	}

	@Test
	public void testAdd() {
		//Case 1: have company number, no dates
		Computer computer = new Computer("testComputer", 1, null, null);
		
		computerDAOImp.add(computer);
         
        List<Computer> computers = computerDAOImp.list();
        Assert.assertEquals(computer.getName(), computers.get(0).getName());
        
        //Case 2: have company number, date of intro
        computer = new Computer("testComputer", 1, Date.valueOf("1997-10-02 00:00:00"), null);
		
		computerDAOImp.add(computer);
         
        computers = computerDAOImp.list();
        Assert.assertEquals(computer.getName(), computers.get(0).getName());
        
        //Case 3: have company number, date of intro and disc superior to date of intro
        computer = new Computer("testComputer", 1, Date.valueOf("1997-10-02 00:00:00"), Date.valueOf("1997-10-03 00:00:00"));
		
		computerDAOImp.add(computer);
         
        computers = computerDAOImp.list();
        Assert.assertEquals(computer.getName(), computers.get(0).getName());
	}

}
