package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class Error500 extends Exception {
  
  private static final long serialVersionUID = 1L;

  public Error500() {
    super("Sewver was not owkay, sending help wight away don't wowwy UwU");
  }

}
