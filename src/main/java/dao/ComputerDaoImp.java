package dao;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import model.Company;
import model.Computer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImp extends Dao implements ComputerDao {

  private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id= ? ";

  private JdbcTemplate jdbcTemplate;

  private Session session;
  private CriteriaBuilder builder;
  private CriteriaQuery<Computer> criteria;
  private Root<Computer> root;

  @Autowired
  private SessionFactory sessionFactory;

  public ComputerDaoImp() {
  }

  private void setCriteria() {
    this.session = getSession();
    this.builder = this.session.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(Computer.class);
    this.root = this.criteria.from(Computer.class);
    criteria.select(root);
  }
  
  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Autowired
  public void setDataSource(HikariDataSource ds) {
    this.jdbcTemplate = new JdbcTemplate(ds);
  }

  @Override
  public List<Computer> list() {
    setCriteria();

    Query<Computer> query = getSession().createQuery(criteria.select(root));
    return query.getResultList();
  }

  @Override
  public List<Computer> orderBy(String column, String order, int limit, int offset) {
    setCriteria();

    if (order.equals("ASC")) {
      criteria.orderBy(builder.asc(root.get(column)));
    } else if (order.equals("DESC")) {
      criteria.orderBy(builder.desc(root.get(column)));
    } else {
      criteria.orderBy(builder.asc(root.get("id")));
    }

    Query<Computer> query = getSession().createQuery(criteria);
    query.setFirstResult(offset);
    query.setMaxResults(limit);

    return query.getResultList();
  }

  @Override
  public List<Computer> listPage(int limit, int offset) {
    setCriteria();

    Query<Computer> query = getSession().createQuery(criteria);
    query.setFirstResult(offset);
    query.setMaxResults(limit);

    return query.getResultList();
  }

  @Override
  public List<Computer> getComputer(Computer computer) {
    setCriteria();

    criteria.select(root).where(builder.equal(root.get("id"), computer.getId()));
    Query<Computer> query = getSession().createQuery(criteria);

    return query.getResultList();
  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    setCriteria();

    criteria.select(root).where(builder.like(root.<String>get("name"), computer.getName() + "%"));
    Query<Computer> query = getSession().createQuery(criteria);
    return query.getResultList();
  }

  @Override
  public Long getMaxId() {
    setCriteria();

    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
    Root<Computer> root = criteriaQuery.from(Computer.class);

    criteriaQuery.select(builder.count(root));
    Query<Long> query = session.createQuery(criteriaQuery);

    return query.getSingleResult();
  }

  @Override
  public void delete(Computer computer) {
    CriteriaBuilder deleteBuilder = getSession().getCriteriaBuilder();
    CriteriaDelete<Computer> delete = deleteBuilder.createCriteriaDelete(Computer.class);
    Root<Computer> deleteRoot = delete.from(Computer.class);

    delete.where(deleteBuilder.equal(deleteRoot.get("id"), computer.getId()));

    getSession().createQuery(delete).executeUpdate();
  }

  @Override
  public void update(Computer computer) {

    CriteriaBuilder updateBuilder = getSession().getCriteriaBuilder();
    CriteriaUpdate<Computer> update = updateBuilder.createCriteriaUpdate(Computer.class);
    Root<Computer> updateRoot = update.from(Computer.class);

    update.set("name", computer.getName());
    update.set("intro", computer.getIntro());
    update.set("discontinuation", computer.getDiscontinuation());
    update.set("company", computer.getCompany());
    update.where(updateBuilder.equal(updateRoot.get("id"), computer.getId()));

    getSession().createQuery(update).executeUpdate();
  }

  @Override
  public void add(Computer computer) {
    setCriteria();
    session.save(computer);//Name not added somehow
  }

  @Override
  public void deleteComputerOfCompanyId(Company company) {
    this.jdbcTemplate.update(DELETE_COMPUTER, company.getId());
  }

  /**
   * Changes a date to a timestamp.
   *
   * @param date the date
   * @return the timestamp
   */
  public Timestamp toTimestamp(Date date) {

    if (null != date) {
      return new Timestamp(date.getTime());
    } else {
      return null;
    }

  }

}
