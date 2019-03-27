package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {
  
  @Id
  @Column(name = "user_role_id")
  private   Integer UserRoleId;
  
  @ManyToOne
  @JoinColumn(name = "username")
  private User user;
  
  @Column
  private String role;
  

  public UserRole() {
  }

  public UserRole(User user, String role) {
    this.user = user;
    this.role = role;
  }

  public Integer getUserRoleId() {
    return UserRoleId;
  }

  public void setUserRoleId(Integer userRoleId) {
    UserRoleId = userRoleId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserRole [role=" + role + "]";
  }

}
