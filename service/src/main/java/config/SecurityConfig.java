package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserService userDetailsService;
  
  @Autowired
  public SecurityConfig(UserService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

//  @Override
//  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService);
//  }
  
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication()
        .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
        .and()
        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
    .csrf().disable()
    .authorizeRequests()
    .antMatchers("/EditComputer*","/AddComputer*", "/Dashboard*").hasRole("ADMIN")
    .antMatchers("/Dashboard*").hasRole("USER")
    .antMatchers("/login*", "/logout").permitAll() 
    .anyRequest().authenticated()
    .and()
    .formLogin()  
    .defaultSuccessUrl("/Dashboard", true)
    .and()
    .logout()
    .logoutUrl("/logout")
    .deleteCookies("JSESSIONID");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
