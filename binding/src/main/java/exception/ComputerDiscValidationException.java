package exception;

public class ComputerDiscValidationException extends ComputerValidationException {
  
  private static final long serialVersionUID = 1L;

  public ComputerDiscValidationException() {
    super("Date of Discontinuation isn't of the valid format.");
  }
}
