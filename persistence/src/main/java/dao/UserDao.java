package dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import model.UserDto;

@Repository
public class UserDao extends Dao{
  private Session session;
  private CriteriaBuilder builder;
  private CriteriaQuery<User> criteria;
  private Root<User> root;

  public UserDao() {
  }

  private void setCriteria() {
    this.session = getSession();
    this.builder = this.session.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(User.class);
    this.root = this.criteria.from(User.class);
    criteria.select(root);
  }
  
  public User login(User user) {
    setCriteria();
    criteria.select(root).where(builder.equal(root.get("name"), user.getUsername()));
    return null;
  }

  public User findByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

}
