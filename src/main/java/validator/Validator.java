package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

  private static final Logger logger = LoggerFactory.getLogger(Validator.class);

  /**
   * Validate name.
   *
   * @param name the name
   * @return true, if successful
   */
  public static boolean validateName(String name) {
    if (!name.equals("")) {
      logger.info("valid");
      return true;
    } else {
      return false;
    }
  }

  /**
   * Validate intro.
   *
   * @param intro the intro
   * @return true, if successful
   */
  public static boolean validateIntro(String intro) {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    try {
      if (!intro.equals("")) {
        dt.parse(intro);
      }
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  /**
   * Validate disc.
   *
   * @param disc the disc
   * @param intro the intro
   * @return true, if successful
   */
  public static boolean validateDisc(String disc, Date intro) {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    Date discDate = null;
    try {
      if (!disc.equals("")) {
        discDate = dt.parse(disc);
        if (discDate.before(intro)) {
          logger.info("The date you entered happened before the date of introduction. "
              + "Please enter a valid date.");
          return false;
        }
      }
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  /**
   * Validate id.
   *
   * @param id the id
   * @return true, if successful
   */
  public static boolean validateId(int id) {
    if (id > 43 || id < 0) {
      return false;
    } else {
      return true;
    }
  }

}
