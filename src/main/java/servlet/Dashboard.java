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
        page = Integer.parseInt(pageString);
      }
    } catch (NullPointerException e) {
      logger.error("PageString not valid");
    }

    List<Computer> computers = ServiceComputer.getInstance().listPage(page);
    logger.debug("Size of computers: " + computers.size());
    request.setAttribute("computers", computers);
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
    String idString = request.getParameter("selection");
    String[] idStringTable = idString.split(",");
    for (String c : idStringTable) {
      int id = Integer.parseInt(c);
      Computer computer = ServiceComputer.getInstance().getComputer(id).get(0);
      logger.debug("Deleting computer: " + computer.getName());
      ServiceComputer.getInstance().delete(computer.getName());
    }
  }

}
