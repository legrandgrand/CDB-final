package dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends Dao{
  private Session session;
  private CriteriaBuilder builder;
  private CriteriaQuery<User> criteria;
  private Root<User> root;

  private UserDao() {
  }

  private void setCriteria() {
    this.session = getSession();
    this.builder = this.session.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(User.class);
    this.root = this.criteria.from(User.class);
    criteria.select(root);
  }

  public User findByUsername(String username) {
    setCriteria();
    criteria.select(root).where(builder.equal(root.get("name"), username));
    Query<User> query = getSession().createQuery(criteria);
    User user = query.getSingleResult();
    System.out.println(user);
    return user;
  }

}
