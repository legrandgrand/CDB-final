package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Company;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CompanyDaoImp extends Dao implements CompanyDao {

  private Session session;
  private CriteriaBuilder builder;
  private CriteriaQuery<Company> criteria;
  private Root<Company> root;
  
  private void setCriteria() {
    this.session = getSession();
    this.builder = this.session.getCriteriaBuilder();
    this.criteria = this.builder.createQuery(Company.class);
    this.root = this.criteria.from(Company.class);
    criteria.select(root);
  }

  public CompanyDaoImp() {
  }

  @Override
  public List<Company> list() {
    setCriteria();

    Query<Company> query = getSession().createQuery(criteria.select(root));
    return query.getResultList();
  }

  @Override
  public List<Company> getCompany(Company company) {
    setCriteria();

    criteria.select(root).where(builder.like(root.<String>get("name"), company.getName() + "%"));
    Query<Company> query = getSession().createQuery(criteria);
    return query.getResultList();
  }

  @Override
  public List<Company> getCompanyFromId(Company company) {
    setCriteria();

    criteria.select(root).where(builder.equal(root.get("id"), company.getId()));
    Query<Company> query = getSession().createQuery(criteria);

    return query.getResultList();
  }

  @Override
  public void delete(Company company) {
    CriteriaBuilder deleteBuilder = getSession().getCriteriaBuilder();
    CriteriaDelete<Company> delete = deleteBuilder.createCriteriaDelete(Company.class);
    Root<Company> deleteRoot = delete.from(Company.class);

    delete.where(deleteBuilder.equal(deleteRoot.get("company"), company.getId()));

    getSession().createQuery(delete).executeUpdate();
  }

}
