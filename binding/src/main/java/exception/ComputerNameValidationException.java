package exception;

public class ComputerNameValidationException extends ComputerValidationException {
  
  private static final long serialVersionUID = 1L;

  public ComputerNameValidationException() {
    super("The name you entered is empty. Please try again.");
  }

}
