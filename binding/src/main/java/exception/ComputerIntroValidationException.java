package exception;

public class ComputerIntroValidationException extends ComputerValidationException {
  
  private static final long serialVersionUID = 1L;

  public ComputerIntroValidationException() {
    super("Date of introduction isn't of the valid format.");
  }

}
