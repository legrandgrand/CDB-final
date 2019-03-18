package dao;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import mapper.RowMapperComputer;

import model.Company;
import model.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImp extends Dao implements ComputerDao {

  private static final String INSERT = "INSERT INTO computer "
      + "(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
  private static final String UPDATE = "UPDATE computer "
      + "SET name=?, introduced = ?, discontinued = ?, company_id = ? WHERE name= ?";
  private static final String SELECT = "SELECT computer.id, computer.name, introduced, "
      + "discontinued, company_id, company.name "
      + "FROM computer LEFT JOIN company ON company_id=company.id ";
  private static final String SELECT_ID = SELECT + " WHERE computer.id=?";
  private static final String SELECT_NAME = SELECT 
      + "WHERE computer.name LIKE '%";  
  private static final String SELECT_ORDER_BY = SELECT + " ORDER BY ISNULL(computer.";
  private static final String PAGE = SELECT + " LIMIT ? OFFSET ? ";
  private static final String GET_MAX_ID = "SELECT MAX(id) FROM computer";
  private static final String DELETE = "DELETE FROM computer WHERE name= ?";
  private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id= ? ";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(HikariDataSource ds) {
    this.jdbcTemplate = new JdbcTemplate(ds);
  }

  private ComputerDaoImp() {
  }

  @Override
  public List<Computer> list() {
    return this.jdbcTemplate.query(SELECT, new RowMapperComputer());
  }

  @Override
  public List<Computer> orderBy(String column, String type, int limit, int offset) {
    String sql = SELECT_ORDER_BY + column + ") , computer." + column + " " + type + " LIMIT " 
        + limit + " OFFSET " + offset;
    return this.jdbcTemplate.query(sql, new RowMapperComputer());
  }

  @Override
  public List<Computer> listPage(int limit, int page) {
    return this.jdbcTemplate.query(PAGE, new RowMapperComputer(), limit, page);
  }

  @Override
  public List<Computer> getComputer(Computer computer) {
    return this.jdbcTemplate.query(SELECT_ID, new RowMapperComputer(), computer.getId());
  }

  @Override
  public int getMaxId() {
    return this.jdbcTemplate.queryForObject(GET_MAX_ID, Integer.class);
  }

  @Override
  public List<Computer> getComputerFromName(Computer computer) {
    String sql = SELECT_NAME + computer.getName() + "%'";
    return this.jdbcTemplate.query(sql, new RowMapperComputer());
  }

  @Override
  public void delete(Computer computer) {
    this.jdbcTemplate.update(DELETE, computer.getName());
  }

  @Override
  public void update(Computer computer) {
    
    Timestamp introduced = toTimestamp(computer.getIntro());
    Timestamp discontinued = toTimestamp(computer.getDiscontinuation());

    this.jdbcTemplate.update(UPDATE, computer.getName(), introduced, 
        discontinued, computer.getCompany().getId(), computer.getName());
  }

  @Override
  public void add(Computer computer) {
    Timestamp introduced = toTimestamp(computer.getIntro());
    Timestamp discontinued = toTimestamp(computer.getDiscontinuation());
    
    this.jdbcTemplate.update(INSERT, computer.getName(), introduced, 
        discontinued, computer.getCompany().getId());
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
