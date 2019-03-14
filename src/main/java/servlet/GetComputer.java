package servlet;

import dto.ComputerDto;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapper.Mapper;

import model.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.ServiceComputer;

/**
 * Servlet implementation class GetComputer.
 */
@WebServlet("/GetComputer")
@Configurable
public class GetComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ServiceComputer serviceComputer;
  
  @Autowired
  private Mapper mapper;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Computer computer = new Computer();
    computer.setName(request.getParameter("search"));
    // TODO: get computers from companyName
    List<Computer> computers = serviceComputer.getComputerFromName(computer);
    List<ComputerDto> dto = mapper.listDtos(computers);
    
    String order = request.getParameter("Order");

    request.setAttribute("computers", dto);

    request.setAttribute("maxId", dto.size());

    if (order.equals("ASC")) {
      order = "DESC";
    } else {
      order = "ASC";
    }
    
    request.setAttribute("Order", order);
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
        response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
