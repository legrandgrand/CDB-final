package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDto;
import mapper.Mapper;
import service.ServiceComputer;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(Dashboard.class);

  private ServiceComputer serviceComputer = ServiceComputer.getInstance();

  /**
   * Do get.
   * 
   * @param request  the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int page = setPage(request);
    int limit = setLimit(request);
    
    
    List<Computer> computers = serviceComputer.listPage(limit, page);
    List<ComputerDto> dto = Mapper.getInstance().listDtos(computers);
    
    request.setAttribute("page", page / 20);
    request.setAttribute("maxId", serviceComputer.getMaxId()); 
    request.setAttribute("limit", limit);
    request.setAttribute("computers", dto);
    request.setAttribute("Order", "ASC");

    this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,
        response);
  }

  /**
   * Do post.
   *
   * @param request  the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException      Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

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
    limitString = request.getParameter("limit");
    try {
      if (!limitString.equals("")) {
        limit = Integer.parseInt(limitString);
      }
    } catch (NullPointerException se) {
      logger.error("not valid");
    } catch (NumberFormatException se) {
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
    pageString = request.getParameter("page");
    try {
      if (!pageString.equals("")) {
        page = Integer.parseInt(pageString) * 20;
      }
    } catch (NullPointerException e) {
      logger.error("not valid");
    } catch (NumberFormatException e) {
      logger.error("PageString not valid");
    }
    Math.floor(page);
    return page;
  }

}
