package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import service.ServiceComputer;

/**
 * Servlet implementation class OrderByName
 */
@WebServlet("/OrderByCompany")
public class OrderByCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(OrderByCompany.class);
  
  private ServiceComputer serviceComputer = ServiceComputer.getInstance();
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String type = request.getQueryString();
    logger.debug("requestis order by: " + type);
    List<Computer> computers = serviceComputer.orderByCompany(type);
    
    logger.debug("Size of computers: " + computers.size());
    request.setAttribute("computers", computers);
    
    request.setAttribute("maxId", computers.size());
    
    request.setAttribute("Order", "Descend");
    this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,
        response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub orderByName
		doGet(request, response);
	}

}
