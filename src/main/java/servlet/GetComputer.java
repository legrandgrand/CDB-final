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
 * Servlet implementation class GetComputer
 */
@WebServlet("/GetComputer")
public class GetComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(GetComputer.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String computerName = request.getParameter("search");
    List<Computer> computers = ServiceComputer.getInstance().getComputerFromName(computerName);
    logger.debug("Size of computers: " + computers.size());
    request.setAttribute("computers", computers);
	  this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,
        response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
