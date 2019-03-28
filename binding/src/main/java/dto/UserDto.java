package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import validator.ValidatePasswordAnnotation.PasswordMatches;

@PasswordMatches
public class UserDto {
  @NotNull
  @NotEmpty
  private String username;
   
  @NotNull
  @NotEmpty
  private String password;
  private String matchingPassword;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  @Override
  public String toString() {
    return "UserDto [username=" + username + ", password=" + password + ", matchingPassword="
        + matchingPassword + "]";
  }
  
  

}
