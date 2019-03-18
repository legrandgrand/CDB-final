package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

import org.springframework.jdbc.core.RowMapper;

public class RowMapperCompany implements RowMapper<Company>  {

  @Override
  public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
    Company company = new Company();
    company.setId(rs.getInt("id"));
    company.setName(rs.getString("name"));
    return company;
  }

}
