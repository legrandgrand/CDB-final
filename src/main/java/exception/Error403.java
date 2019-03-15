package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN) 
public class Error403 extends Exception {

  private static final long serialVersionUID = 1L;

  public Error403() {
    super("OwO you can't do that");
  }

}
