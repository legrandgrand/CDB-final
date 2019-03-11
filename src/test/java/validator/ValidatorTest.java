package validator;

import config.SpringConfig;

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
  ComputerValidator validator;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(SpringConfig.class);
  }
  
  @Test
  public void shouldCorrectName() throws ComputerValidationException {
    validator.validateName("test");      
  } 
  
  @Test(expected = ComputerValidationException.class)
  public void shouldNullName() throws ComputerValidationException {
    validator.validateName("");
  }

  @Test
  public void shouldParseCorrectDateIntro() throws ComputerValidationException {
    validator.validateDateFormatIntro("1999-07-01");
  }
  
  @Test
  public void shouldParseNullDateIntro() throws ComputerValidationException {  
    validator.validateDateFormatIntro("");
  }
  
  @Test(expected = ComputerValidationException.class)
  public void shouldParseInvalidDateIntro() throws ComputerValidationException {    
    validator.validateDateFormatIntro("invalid date");
  }

  @Test(expected = ComputerValidationException.class)
  public void testValidateDisc() throws ComputerValidationException {
    String disc = "1999-07-01";
    String intro = "1998-07-01";
    Date dateIntro = null;
    try {
      dateIntro = dt.parse(intro);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    System.out.println(dateIntro);
    validator.validateDateFormatDisc(disc);
    disc = "";
    validator.validateDateFormatDisc(disc);
    disc = "invalid date";
    validator.validateDateFormatDisc(disc);
    disc = "1997-07-01";
    try {
      dateIntro = dt.parse(intro);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    validator.validateDateFormatDisc(disc);
    
  }

  @Test(expected = ComputerValidationException.class)
  public void testValidateId() throws ComputerValidationException {
    int id = 0;
    validator.validateId(id);
    id = 2;
    validator.validateId(id);
    id = 60;
    validator.validateId(id);
    id = -2;
    validator.validateId(id);
  }

}
