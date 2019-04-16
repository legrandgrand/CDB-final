package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Company;


import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImp extends Dao implements CompanyDao {

  private CriteriaBuilder builder;
  private CriteriaQuery<Company> criteria;
  private Root<Company> root;
  
  private void setCriteria() {
    this.builder = entityManager.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(Company.class);
    this.root = this.criteria.from(Company.class);
  }

  public CompanyDaoImp() {
  }

  @Override
  public List<Company> list() {
    setCriteria();
    criteria.select(root);
    
    return entityManager.createQuery(criteria.select(root)).getResultList();
  }

  @Override
  public List<Company> getCompany(Company company) {
    setCriteria();

    criteria.select(root).where(builder.like(root.<String>get("name"), company.getName() + "%"));
    
    return entityManager.createQuery(criteria).getResultList();
  }

  @Override
  public List<Company> getCompanyFromId(long id) {
    setCriteria();

    criteria.select(root).where(builder.equal(root.get("id"), id));
    
    return entityManager.createQuery(criteria).getResultList();
  }

  @Override
  public void delete(Company company) {
    CriteriaBuilder deleteBuilder = entityManager.getCriteriaBuilder();
    CriteriaDelete<Company> delete = deleteBuilder.createCriteriaDelete(Company.class);
    Root<Company> deleteRoot = delete.from(Company.class);

    delete.where(deleteBuilder.equal(deleteRoot.get("company"), company.getId()));

    entityManager.createQuery(delete).executeUpdate();
  }

}
