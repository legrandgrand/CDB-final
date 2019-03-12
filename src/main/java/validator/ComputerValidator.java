package validator;

import dto.ComputerDto;
import exception.ComputerDiscValidationException;
import exception.ComputerIdValidationException;
import exception.ComputerIntroValidationException;
import exception.ComputerNameValidationException;
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

  /**
   * Instantiates a new computer validator.
   */
  private ComputerValidator() {}
  
  /**
   * Validate dto.
   *
   * @param dto the dto
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateDto(ComputerDto dto) throws ComputerValidationException {
    validateName(dto.getName());
    validateDateFormatDisc(dto.getDateDiscontinuation());
    validateDateFormatIntro(dto.getDateIntro());
  }

  /**
   * Validate name.
   *
   * @param name the name
   * @throws ComputerNameValidationException the computer name validation exception
   */
  public void validateName(String name) throws ComputerNameValidationException {
    if (!name.equals("")) {
      logger.info("valid");
    } else {
      throw new ComputerNameValidationException("Invalid name");
    }
  }

  /**
   * Validate intro.
   *
   * @param intro the intro
   * @throws ComputerIntroValidationException the computer intro validation exception
   */
  public void validateDateFormatIntro(String intro) throws ComputerIntroValidationException {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    try {
      if (!intro.equals("")) {
        dt.parse(intro);
      }
    } catch (ParseException e) {
      throw new ComputerIntroValidationException("Invalid type of Introduction");
    }
  }

  /**
   * Validate disc.
   *
   * @param disc  the disc
   * @throws ComputerDiscValidationException the computer disc validation exception
   */
  public void validateDateFormatDisc(String disc) throws ComputerDiscValidationException {
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    try {
      if (!disc.equals("")) {
        dt.parse(disc);
      }
    } catch (ParseException e) {
      throw new ComputerDiscValidationException("Invalid type of Date of Discontinuation");
    }
  }
  
  /**
   * Validate date of disc.
   *
   * @param intro the intro
   * @param disc the disc
   * @throws ComputerValidationException the computer validation exception
   */
  public void validateDiscBeforeIntro(Date intro, Date disc) 
      throws ComputerValidationException {
    if (disc != null) {
      if (disc.before(intro)) {
        logger.info("");
        throw new ComputerValidationException("The date you entered happened "
            + "before the date of introduction. Please enter a valid date.");
      }
    }
  }

  /**
   * Validate id.
   *
   * @param id the id
   * @throws ComputerIdValidationException the computer id validation exception
   */
  public void validateId(int id) throws ComputerIdValidationException {
    if (id > MAX_ID || id < MIN_ID) {
      throw new ComputerIdValidationException("Invalid company Id");
    }
  }
  


}
