package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import model.Company;
import model.Computer;
import model.Page;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImp extends Dao implements ComputerDao {

  private Session session;
  private CriteriaBuilder builder;
  private CriteriaQuery<Computer> criteria;
  private Root<Computer> root;

  private ComputerDaoImp() {
  }

  private void setCriteria() {
    this.session = getSession();
    this.builder = this.session.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(Computer.class);
    this.root = this.criteria.from(Computer.class);

  }

  @Override
  public List<Computer> list() {
    setCriteria();
    criteria.select(root);
    Query<Computer> query = getSession().createQuery(criteria.select(root));
    return query.getResultList();
  }

  @Override
  public List<Computer> orderBy(Page page) {
    setCriteria();
    if (page.getOrderBy().equals("ASC")) {
      criteria.select(root).orderBy(builder.asc(root.get(page.getType())));
    } else if (page.getOrderBy().equals("DESC")) {
      criteria.select(root).orderBy(builder.desc(root.get(page.getType())));
    } else {
      criteria.select(root).orderBy(builder.asc(root.get("id")));
    }

    Query<Computer> query = getSession().createQuery(criteria);
    query.setFirstResult(page.getOffset());
    query.setMaxResults(page.getLimit());

    return query.getResultList();
  }

  @Override
  public List<Computer> listPage(Page page) {
    setCriteria();

    Query<Computer> query = getSession().createQuery(criteria);
    query.setFirstResult(page.getOffset());
    query.setMaxResults(page.getLimit());

    return query.getResultList();
  }

  @Override
  public List<Computer> getComputer(long id) {
    setCriteria();

    criteria.select(root).where(builder.equal(root.get("id"), id));
    Query<Computer> query = getSession().createQuery(criteria);

    return query.getResultList();
  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    setCriteria();

    criteria.select(root).where(builder.like(root.<String>get("name"), "%" + computer.getName().toLowerCase() + "%"));
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

    if (computer != null) {
      CriteriaBuilder updateBuilder = getSession().getCriteriaBuilder();
      CriteriaUpdate<Computer> update = updateBuilder.createCriteriaUpdate(Computer.class);
      Root<Computer> updateRoot = update.from(Computer.class);

      update.set("name", computer.getName())
          .set("intro", computer.getIntro())
          .set("discontinuation", computer.getDiscontinuation())
          .set("company", computer.getCompany())
          .where(updateBuilder.equal(updateRoot.get("id"), computer.getId()));

      getSession().createQuery(update).executeUpdate();
    }

  }

  @Override
  public void add(Computer computer) {
    
    if (computer != null) {
      getSession().save(computer);
    }

  }

  @Override
  public void deleteComputerOfCompanyId(Company company) {
    CriteriaBuilder deleteBuilder = getSession().getCriteriaBuilder();
    CriteriaDelete<Computer> delete = deleteBuilder.createCriteriaDelete(Computer.class);
    Root<Computer> deleteRoot = delete.from(Computer.class);

    delete.where(deleteBuilder.equal(deleteRoot.get("company"), company.getId()));

    getSession().createQuery(delete).executeUpdate();
  }

}
