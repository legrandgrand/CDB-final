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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.ServiceComputer;

/**
 * Servlet implementation class OrderByName.
 */
@WebServlet("/OrderByIntro")
@Configurable
public class OrderByIntro extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(OrderByIntro.class);

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
    int page = setPage(request);
    int limit = setLimit(request);
    
    String type = request.getParameter("Order");
    
    List<Computer> computers = serviceComputer.orderBy("introduced", type, limit, page);
    List<ComputerDto> dto = mapper.listDtos(computers);
    
    request.setAttribute("page", page / 20);
    request.setAttribute("maxId", serviceComputer.getMaxId()); 
    request.setAttribute("limit", limit);  
    request.setAttribute("computers", dto);

    if (type.equals("ASC")) {
      type = "DESC";
    } else {
      type = "ASC";
    }
    request.setAttribute("Order", type);
    logger.debug("request is order by: " + type);
    this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,
        response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
  /**
   * Sets the limit.
   *
   * @param request the request
   * @return the int
   */
  public int setLimit(HttpServletRequest request) {
    String limitString = null;
    int limit = 20;
    try {
      limitString = request.getParameter("limit");
      if (limitString != null) {
        limit = Integer.parseInt(limitString);
      }
    }  catch (NumberFormatException se) {
      logger.error("PageString not valid");
    }
    return limit;
  }
  
  /**
   * Sets the page.
   *
   * @param request the request
   * @return the int
   */
  public int setPage(HttpServletRequest request) {
    String pageString = null;
    int page = 0;
    try {
      pageString = request.getParameter("page");
      if (pageString != null) {
        page = Integer.parseInt(pageString) * 20;
      }
    } catch (NumberFormatException e) {
      logger.error("PageString not valid");
    }
    return page;
  }

}
