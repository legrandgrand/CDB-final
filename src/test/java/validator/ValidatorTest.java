//package validator;
//
//import static org.junit.Assert.assertTrue;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.Test;
//
//public class ValidatorTest {
//  SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
//  
//  @Test
//  public void testValidateName() {
//    String name = "test";
//    assertTrue(Validator.validateName(name));
//    name = "";
//    assertTrue(!Validator.validateName(name));
//        
//  }
//
//  @Test
//  public void testValidateIntro() {
//    String intro = "1999-07-01";
//    assertTrue(Validator.validateIntro(intro));
//    intro = "";
//    assertTrue(Validator.validateIntro(intro));
//    intro = "invalid date";
//    assertTrue(!Validator.validateIntro(intro));
//  }
//
//  @Test
//  public void testValidateDisc() {
//    String disc = "1999-07-01";
//    String intro = "1998-07-01";
//    Date dateIntro = null;
//    try {
//      dateIntro = dt.parse(intro);
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }
//    System.out.println(dateIntro);
//    assertTrue(Validator.validateDisc(disc, dateIntro));
//    disc = "";
//    assertTrue(Validator.validateDisc(disc, dateIntro));
//    disc = "invalid date";
//    assertTrue(!Validator.validateDisc(disc, dateIntro));
//    disc = "1997-07-01";
//    try {
//      dateIntro = dt.parse(intro);
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }
//    assertTrue(!Validator.validateDisc(disc, dateIntro));
//    
//  }
//
//  @Test
//  public void testValidateId() {
//    int id = 0;
//    assertTrue(Validator.validateId(id));
//    id = 2;
//    assertTrue(Validator.validateId(id));
//    id = 60;
//    assertTrue(!Validator.validateId(id));
//    id = -2;
//    assertTrue(!Validator.validateId(id));
//  }
//
//}
