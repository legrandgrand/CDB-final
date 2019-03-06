package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

  private static final Logger logger = LoggerFactory.getLogger(Validator.class);
  private static final int MIN_ID = 0;
  private static final int MAX_ID = 43;

  /**
   * Validate name.
   *
   * @param name the name
   * @throws Exception the exception
   */
  public static void validateName(String name) throws Exception {
    if (!name.equals("")) {
      logger.info("valid");
    } else {
      throw new Exception("Invalid name");
    }
  }

  /**
   * Validate intro.
   *
   * @param intro the intro
   * @throws Exception the exception
   */
  public static void validateIntro(String intro) throws Exception {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    try {
      if (!intro.equals("")) {
        dt.parse(intro);
      }
    } catch (ParseException e) {
      throw new Exception("Invalid date of introduction");
    }
  }

  /**
   * Validate disc.
   *
   * @param disc  the disc
   * @param intro the intro
   * @throws Exception the exception
   */
  public static void validateDisc(String disc, Date intro) throws Exception {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    Date discDate = null;
    try {
      if (!disc.equals("")) {
        discDate = dt.parse(disc);
        
        if (discDate.before(intro)) {
          logger.info("");
          throw new Exception("The date you entered happened before the date of introduction. "
              + "Please enter a valid date.");
        }
      }
    } catch (ParseException e) {
      throw new Exception(e);
    }
  }

  /**
   * Validate id.
   *
   * @param id the id
   * @throws Exception the exception
   */
  public static void validateId(int id) throws Exception {
    if (id > MAX_ID || id < MIN_ID) {
      throw new Exception("Invalid company Id");
    }
  }

}
