package dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.User;

@Repository
@Transactional
public class UserDao extends Dao {

  private CriteriaBuilder builder;
  private CriteriaQuery<User> criteria;
  private Root<User> root;

  public UserDao() {
  }

  private void setCriteria() {
    this.builder = entityManager.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(User.class);
    this.root = this.criteria.from(User.class);
    criteria.select(root);
  }

  public User findByUsername(String username) {
    setCriteria();
    User user = null;
    criteria.select(root).where(builder.equal(root.get("username"), username));
    Query<User> query = getSession().createQuery(criteria);
    
    try {
      user = query.getSingleResult();
      user.toString();// Apparently necessary???????????????????????
    } catch (NoResultException e) {

    }

    return user;
  }

  public model.User save(User user) {
    getSession().save(user);
    return user;
  }

}
