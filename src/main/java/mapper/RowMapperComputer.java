package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;
import model.Computer;

import org.springframework.jdbc.core.RowMapper;

public class RowMapperComputer implements RowMapper<Computer> {
  
  @Override
  public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
    Computer computer = new Computer();
    computer.setId(rs.getInt("computer.id"));
    computer.setName(rs.getString("computer.name"));
    computer.setDateIntro(rs.getDate("introduced"));
    computer.setDateDiscontinuation(rs.getDate("discontinued"));
    computer.setCompany(new Company(rs.getString("company.name"), rs.getInt("company_id")));
    return computer;
  }

}
