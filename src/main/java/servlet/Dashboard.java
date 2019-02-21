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

import controller.Controller;
import dao.ComputerDaoImp;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(Controller.class);
  
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	  List<Computer> computers = ServiceComputer.getInstance().listComputer();
	  logger.debug("Size of computers: "+ computers.size());
	  request.setAttribute("computers", computers);  
	  this.getServletContext().getRequestDispatcher( "/views/dashboard.jsp" ).forward( request, response );
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	  String name = request.getParameter("selection");
//	  ServiceComputer.getInstance().deleteComputer(name);
		doGet(request, response);
	}

}
