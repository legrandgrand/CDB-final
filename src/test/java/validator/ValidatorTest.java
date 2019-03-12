package validator;

import config.SpringConfigTest;
import exception.ComputerValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValidatorTest {
  SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
  
  @Autowired
  private static ComputerValidator computerValidator;
  
  private static ApplicationContext applicationContext;  
  
  /**
   * Sets the up before class.
   *
   * @throws Exception the exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    applicationContext = 
        new AnnotationConfigApplicationContext(SpringConfigTest.class);
    computerValidator = applicationContext.getBean("computerValidator", ComputerValidator.class);
  }
  
  @AfterClass
  public static void setUpAfterClass() throws Exception {
    ((ConfigurableApplicationContext)applicationContext).close();
  }
  
  /**
   * Should correct name.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void shouldCorrectName() throws ComputerValidationException {
    computerValidator.validateName("test");      
  } 
  
  /**
   * Should null name.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test(expected = ComputerValidationException.class)
  public void shouldNullName() throws ComputerValidationException {
    computerValidator.validateName("");
  }

  /**
   * Should parse correct date intro.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void shouldParseCorrectDateIntro() throws ComputerValidationException {
    computerValidator.validateDateFormatIntro("1999-07-01");
  }
  
  /**
   * Should parse null date intro.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void shouldParseNullDateIntro() throws ComputerValidationException {  
    computerValidator.validateDateFormatIntro("");
  }
  
  /**
   * Should parse invalid date intro.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test(expected = ComputerValidationException.class)
  public void shouldParseInvalidDateIntro() throws ComputerValidationException {    
    computerValidator.validateDateFormatIntro("invalid date");
  }
  
  /**
   * Should parse correct date disc.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void shouldParseCorrectDateDisc() throws ComputerValidationException {
    computerValidator.validateDateFormatDisc("1999-07-01");
  }
  
  /**
   * Should parse null date disc.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void shouldParseNullDateDisc() throws ComputerValidationException {  
    computerValidator.validateDateFormatDisc("");
  }
  
  /**
   * Should parse invalid date disc.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test(expected = ComputerValidationException.class)
  public void shouldParseInvalidDateDisc() throws ComputerValidationException {    
    computerValidator.validateDateFormatDisc("invalid date");
  }

  /**
   * Should valid date disc.
   *
   * @throws ComputerValidationException the computer validation exception
   */
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
  
  /**
   * Should valid date disc null.
   *
   * @throws ComputerValidationException the computer validation exception
   */
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
  
  /**
   * Should invalid date disc.
   *
   * @throws ComputerValidationException the computer validation exception
   */
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

  /**
   * Test validate id null.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void testValidateIdNull() throws ComputerValidationException {
    int id = 0;
    computerValidator.validateId(id);
  }
  
  /**
   * Test validate id.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test
  public void testValidateId() throws ComputerValidationException {
    int id = 2;
    computerValidator.validateId(id);
  }
  
  /**
   * Test validate id over.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test(expected = ComputerValidationException.class)
  public void testValidateIdOver() throws ComputerValidationException {
    int id = 60;
    computerValidator.validateId(id);
  }
  
  /**
   * Test invalidate id negative.
   *
   * @throws ComputerValidationException the computer validation exception
   */
  @Test(expected = ComputerValidationException.class)
  public void testInvalidateIdNegative() throws ComputerValidationException {
    int id = -2;
    computerValidator.validateId(id);
  }
  

}
