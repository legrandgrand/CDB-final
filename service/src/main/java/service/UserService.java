package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import dto.UserDto;
import exception.UsernameExistsException;
import model.UserRole;

@Service
public class UserService implements UserDetailsService {

  private UserDao userDao;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    model.User user = userDao.findByUsername(username);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

    return buildUserForAuthentication(user, authorities);
  }

  private User buildUserForAuthentication(model.User user, List<GrantedAuthority> authorities) {
    return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
        authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    for (UserRole userRole : userRoles) {
      setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
    }

    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

    return Result;
  }

  @Transactional
  public model.User registerNewUserAccount(UserDto userDto) 
    throws UsernameExistsException {
       
      if (usernameExist(userDto.getUsername())) {  
          throw new UsernameExistsException(
            "There is an account with that email adress: "
            +  userDto.getUsername());
      }
      model.User user = new model.User();    
      user.setUsername(userDto.getUsername());
      user.setPassword(passwordEncoder.encode(userDto.getPassword()));
      user.setEnabled(true);
      return userDao.save(user); 
  }
  
  private boolean usernameExist(String username) {
      model.User user = userDao.findByUsername(username);
      if (user != null) {
          return true;
      }
      return false;
  }
  


}
