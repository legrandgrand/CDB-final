package dao;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Company;
import model.Computer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ComputerDaoImp extends Dao implements ComputerDao {

  private static final String INSERT = "INSERT INTO computer "
      + "(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
  private static final String UPDATE = "UPDATE computer "
      + "SET name=?, introduced = ?, discontinued = ?, company_id = ? WHERE name= ?";
  private static final String GET_MAX_ID = "SELECT COUNT(*) FROM computer";
  private static final String DELETE = "DELETE FROM computer WHERE name= ?";
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

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  private void setCriteria() {
    this.session = getSession();
    this.builder = this.session.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(Computer.class);
    this.root = this.criteria.from(Computer.class);
    criteria.select(root);
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
  public int getMaxId() {
    // return criteria.setProjection(Projections.rowCount()).uniqueResult().hashCode();
    return this.jdbcTemplate.queryForObject(GET_MAX_ID, Integer.class);
  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    setCriteria();

    criteria.select(root).where(builder.like(root.<String>get("name"), computer.getName() + "%"));
    Query<Computer> query = getSession().createQuery(criteria);
    return query.getResultList();
  }

  @Override
  public void delete(Computer computer) {
    CriteriaBuilder deletebuilder = getSession().getCriteriaBuilder();
    CriteriaDelete<Computer> delete = deletebuilder.createCriteriaDelete(Computer.class);
    Root<Computer> deleteRoot = delete.from(Computer.class);
    delete.where(deletebuilder.equal(deleteRoot.get("id"), computer.getId()));
    getSession().createQuery(delete).executeUpdate();
    //this.jdbcTemplate.update(DELETE, computer.getName());
  }

  @Override
  public void update(Computer computer) {

    Timestamp introduced = toTimestamp(computer.getIntro());
    Timestamp discontinued = toTimestamp(computer.getDiscontinuation());

    this.jdbcTemplate.update(UPDATE, computer.getName(), introduced, discontinued,
        computer.getCompany().getId(), computer.getName());
  }

  @Override
  public void add(Computer computer) {
    Timestamp introduced = toTimestamp(computer.getIntro());
    Timestamp discontinued = toTimestamp(computer.getDiscontinuation());

    this.jdbcTemplate.update(INSERT, computer.getName(), introduced, discontinued,
        computer.getCompany().getId());
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
