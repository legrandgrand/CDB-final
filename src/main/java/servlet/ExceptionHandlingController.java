package servlet;

import exception.Error403;
import exception.Error404;
import exception.Error500;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandlingController {
  //Does not work
  @ExceptionHandler(Error404.class)
  public ModelAndView error404(HttpServletRequest req) {
    return new ModelAndView("404");
  }
  
  @ExceptionHandler(Error500.class)
  public ModelAndView error500(HttpServletRequest req) {
    return new ModelAndView("500");
  }
  
  @ExceptionHandler(Error403.class)
  public ModelAndView error403(HttpServletRequest req) {
    return new ModelAndView("403");
  }

}
