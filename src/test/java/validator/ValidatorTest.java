package validator;

import config.SpringConfigTest;
import dao.ComputerDaoImp;
import exception.ComputerValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValidatorTest {
  SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
  
  @Autowired
  private static ComputerValidator computerValidator;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(SpringConfigTest.class);
    computerValidator = applicationContext.getBean("computerValidator", ComputerValidator.class);
  }
  
  @Test
  public void shouldCorrectName() throws ComputerValidationException {
    computerValidator.validateName("test");      
  } 
  
  @Test(expected = ComputerValidationException.class)
  public void shouldNullName() throws ComputerValidationException {
    computerValidator.validateName("");
  }

  @Test
  public void shouldParseCorrectDateIntro() throws ComputerValidationException {
    computerValidator.validateDateFormatIntro("1999-07-01");
  }
  
  @Test
  public void shouldParseNullDateIntro() throws ComputerValidationException {  
    computerValidator.validateDateFormatIntro("");
  }
  
  @Test(expected = ComputerValidationException.class)
  public void shouldParseInvalidDateIntro() throws ComputerValidationException {    
    computerValidator.validateDateFormatIntro("invalid date");
  }
  
  @Test
  public void shouldParseCorrectDateDisc() throws ComputerValidationException {
    computerValidator.validateDateFormatDisc("1999-07-01");
  }
  
  @Test
  public void shouldParseNullDateDisc() throws ComputerValidationException {  
    computerValidator.validateDateFormatDisc("");
  }
  
  @Test(expected = ComputerValidationException.class)
  public void shouldParseInvalidDateDisc() throws ComputerValidationException {    
    computerValidator.validateDateFormatDisc("invalid date");
  }

  @Test
  public void shouldValidDateDisc() throws ComputerValidationException {
    String disc = "1999-07-01";
    String intro = "1998-07-01";
    Date dateIntro = null;
    Date dateDisc = null;
    try {
      dateIntro = dt.parse(intro);
      dateDisc = dt.parse(disc);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    computerValidator.validateDiscBeforeIntro(dateIntro, dateDisc);
  }
  
  @Test
  public void shouldValidDateDiscNull() throws ComputerValidationException  {
    String intro = "1998-07-01";
    Date dateIntro = null;
    Date dateDisc = null;
    try {
      dateIntro = dt.parse(intro);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    computerValidator.validateDiscBeforeIntro(dateIntro, dateDisc);
  }
  
  @Test(expected = ComputerValidationException.class)
  public void shouldInvalidDateDisc() throws ComputerValidationException {
    String disc = "1997-07-01";
    String intro = "1998-07-01";
    Date dateIntro = null;
    Date dateDisc = null;
    try {
      dateIntro = dt.parse(intro);
      dateDisc = dt.parse(disc);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    computerValidator.validateDiscBeforeIntro(dateIntro, dateDisc);
  }

  @Test
  public void testValidateIdNull() throws ComputerValidationException {
    int id = 0;
    computerValidator.validateId(id);
  }
  
  @Test
  public void testValidateId() throws ComputerValidationException {
    int id = 2;
    computerValidator.validateId(id);
  }
  
  @Test(expected = ComputerValidationException.class)
  public void testValidateIdOver() throws ComputerValidationException {
    int id = 60;
    computerValidator.validateId(id);
  }
  
  @Test(expected = ComputerValidationException.class)
  public void testInvalidateIdNegative() throws ComputerValidationException {
    int id = -2;
    computerValidator.validateId(id);
  }
  

}
