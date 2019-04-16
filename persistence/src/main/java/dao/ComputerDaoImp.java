package dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import model.Company;
import model.Computer;
import model.Page;

import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImp extends Dao implements ComputerDao {

  private CriteriaBuilder builder;
  private CriteriaQuery<Computer> criteria;
  private Root<Computer> root;

  private ComputerDaoImp() {  }

  private void setCriteria() {
    this.builder = entityManager.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(Computer.class);
    this.root = this.criteria.from(Computer.class);

  }

  @Override
  public List<Computer> list() {
    setCriteria();
    criteria.select(root);
    
    return entityManager.createQuery(criteria.select(root)).getResultList();
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

    TypedQuery<Computer> query = entityManager.createQuery(criteria);
    query.setFirstResult(page.getOffset());
    query.setMaxResults(page.getLimit());

    return query.getResultList();
  }

  @Override
  public List<Computer> listPage(Page page) {
    setCriteria();

    TypedQuery<Computer> query = entityManager.createQuery(criteria);
    query.setFirstResult(page.getOffset());
    query.setMaxResults(page.getLimit());
    
    return query.getResultList();
  }

  @Override
  public List<Computer> getComputer(long id) {
    setCriteria();

    criteria.select(root).where(builder.equal(root.get("id"), id));
    return entityManager.createQuery(criteria).getResultList();
  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    setCriteria();

    criteria.select(root).where(builder.like(root.<String>get("name"), "%" + computer.getName().toLowerCase() + "%"));
    
    return entityManager.createQuery(criteria).getResultList();
  }

  @Override
  public Long getMaxId() {
    setCriteria();

    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
    Root<Computer> root = criteriaQuery.from(Computer.class);

    criteriaQuery.select(builder.count(root));
    
    return entityManager.createQuery(criteriaQuery).getSingleResult();
  }

  @Override
  public void delete(Computer computer) {
    CriteriaBuilder deleteBuilder = entityManager.getCriteriaBuilder();
    CriteriaDelete<Computer> delete = deleteBuilder.createCriteriaDelete(Computer.class);
    Root<Computer> deleteRoot = delete.from(Computer.class);

    delete.where(deleteBuilder.equal(deleteRoot.get("id"), computer.getId()));

    entityManager.createQuery(delete).executeUpdate();
  }

  @Override
  public void update(Computer computer) {
    
    if (computer != null) {
      CriteriaBuilder updateBuilder = entityManager.getCriteriaBuilder();
      CriteriaUpdate<Computer> update = updateBuilder.createCriteriaUpdate(Computer.class);
      Root<Computer> updateRoot = update.from(Computer.class);

      update.set("name", computer.getName())
          .set("intro", computer.getIntro())
          .set("discontinuation", computer.getDiscontinuation())
          .set("company", computer.getCompany())
          .where(updateBuilder.equal(updateRoot.get("id"), computer.getId()));

      entityManager.createQuery(update).executeUpdate();
      
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
    CriteriaBuilder deleteBuilder = entityManager.getCriteriaBuilder();
    CriteriaDelete<Computer> delete = deleteBuilder.createCriteriaDelete(Computer.class);
    Root<Computer> deleteRoot = delete.from(Computer.class);

    delete.where(deleteBuilder.equal(deleteRoot.get("company"), company.getId()));

    entityManager.createQuery(delete).executeUpdate();
  }

}
