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
    String pageString = null;
    int page = 0;
    try {
      pageString = request.getQueryString();
      if (!pageString.equals("")) {
        page = Integer.parseInt(pageString) * 20;
      }
    } catch (NullPointerException e) {
      logger.error("PageString not valid");
    } catch (NumberFormatException e) {
      logger.error("PageString not valid");
    }

    
    request.setAttribute("maxId", serviceComputer.getMaxId());

    List<Computer> computers = serviceComputer.listPage(page);
    request.setAttribute("computers", computers);
    
    Math.floor(page);
    request.setAttribute("page", page/20);
    String ascend = "Ascend"; 
    request.setAttribute("Order", ascend);

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

}
