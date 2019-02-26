package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

  private static final Logger logger = LoggerFactory.getLogger(Validator.class);

  public static boolean validateName(String name) {
    if (!name.equals("")) {
      logger.info("valid");
      return true;
    } else {
      return false;
    }
  }

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

  public static boolean validateDisc(String intro, Date discontinuation){
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    Date introD = null;
    try {
      if (!intro.equals("")) {
        introD = dt.parse(intro);
      }
      if (discontinuation.before(introD)) {
        logger.info("The date you entered happened before the date of introduction. "
            + "Please enter a valid date.");
        return false;
      }
      return true;
    } catch (ParseException e) {
      return false;
    }
  }
  
  public static boolean validateId(int id) {
      if(id>43||id<0) {
        return false;
      } else {
      return true;
      }
  }

}
