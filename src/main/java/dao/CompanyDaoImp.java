package dao;

import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

import mapper.RowMapperCompany;

import model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImp extends Dao implements CompanyDao {

  private static final String SELECT = "SELECT id, name FROM company";
  private static final String SELECT_ID = SELECT + " WHERE name LIKE ";
  private static final String SELECT_NAME = SELECT + " WHERE id= ?";
  private static final String DELETE_COMPANY = "DELETE FROM company WHERE id= ? ";
  
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(HikariDataSource ds) {
    this.jdbcTemplate = new JdbcTemplate(ds);
  }

  private CompanyDaoImp() {
  }

  @Override
  public List<Company> list() {
    return this.jdbcTemplate.query(SELECT, new RowMapperCompany());
  }

  @Override
  public List<Company> getCompany(Company company) {
    String sql = SELECT_ID + "'%" + company.getName() + "%'";
    return this.jdbcTemplate.query(sql, new RowMapperCompany());
  }

  @Override
  public List<Company> getCompanyFromId(Company company) {
    return this.jdbcTemplate.query(SELECT_NAME, new RowMapperCompany(), company.getId());
  }

  @Override
  public void delete(Company company) {
    this.jdbcTemplate.update(DELETE_COMPANY, company.getId());
  }

}
