package validator;

import exception.ComputerValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ComputerValidator {
  
  private static final int MIN_ID = 0;
  private static final int MAX_ID = 43;
  
  private static final Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

  private ComputerValidator() {}

  /**
   * Validate name.
   *
   * @param name the name
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateName(String name) throws ComputerValidationException {
    if (!name.equals("")) {
      logger.info("valid");
    } else {
      throw new ComputerValidationException("Invalid name");
    }
  }

  /**
   * Validate intro.
   *
   * @param intro the intro
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateDateFormatIntro(String intro) throws ComputerValidationException {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    try {
      if (!intro.equals("")) {
        dt.parse(intro);
      }
    } catch (ParseException e) {
      throw new ComputerValidationException("Invalid type of Introduction");
    }
  }

  /**
   * Validate disc.
   *
   * @param disc  the disc
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateDateFormatDisc(String disc) throws ComputerValidationException {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    try {
      if (!disc.equals("")) {
        dt.parse(disc);
      }
    } catch (ParseException e) {
      throw new ComputerValidationException("Invalid type of Date of Discontinuation");
    }
  }
  
  /**
   * Validate date of disc.
   *
   * @param intro the intro
   * @param disc the disc
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateDisc(Date intro, Date disc) 
      throws ComputerValidationException {
    if (disc.before(intro)) {
      logger.info("");
      throw new ComputerValidationException("The date you entered happened "
          + "before the date of introduction. Please enter a valid date.");
    }
  }

  /**
   * Validate id.
   *
   * @param id the id
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateId(int id) throws ComputerValidationException {
    if (id > MAX_ID || id < MIN_ID) {
      throw new ComputerValidationException("Invalid company Id");
    }
  }
  


}
