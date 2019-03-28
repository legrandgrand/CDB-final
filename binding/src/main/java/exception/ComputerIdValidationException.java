package exception;

public class ComputerIdValidationException extends ComputerValidationException {
  
  private static final long serialVersionUID = 1L;

  public ComputerIdValidationException() {
    super("Invalid company Id");
  }

}
