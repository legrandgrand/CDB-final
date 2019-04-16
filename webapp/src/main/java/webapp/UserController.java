package webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDto;
import exception.UsernameExistsException;
import model.User;
import service.UserService;

@RestController
public class UserController {

  UserService userService;
  
  @Autowired
  private UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/registration")
  public ModelAndView registerUserAccount(@ModelAttribute("user") UserDto userDto,
      BindingResult result, WebRequest request, Errors errors) {
    
    User registered = new User();
    if (!result.hasErrors()) {
      registered = createUserAccount(userDto, result);
    }
    
    if (registered == null) {
      result.rejectValue("username", "message.regError");
    }
    
    if (result.hasErrors()) {
      return new ModelAndView("registration", "user", userDto);
    } else {
      return new ModelAndView("login", "user", userDto);
    }
    
  }

  private User createUserAccount(UserDto userDto, BindingResult result) {
    User registered = null;
    
    try {
      registered = userService.registerNewUserAccount(userDto);
    } catch (UsernameExistsException e) {
      return null;
    }
    
    return registered;
  }
  
  @GetMapping(value = "/registration")
  public ModelAndView showRegistrationForm(WebRequest request, Model model) {
      UserDto userDto = new UserDto();
      model.addAttribute("user", userDto);
      return new ModelAndView("registration");
  }

}
