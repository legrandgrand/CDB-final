package servlet;

import java.io.IOException;
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
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
  private static final Logger logger = LoggerFactory.getLogger(DeleteComputer.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idString = request.getParameter("selection");
    String[] idStringTable = idString.split(",");
    for (String c : idStringTable) {
      int id = Integer.parseInt(c);
      Computer computer = ServiceComputer.getInstance().getComputer(id).get(0);
      logger.debug("Deleting computer: " + computer.getName());
      ServiceComputer.getInstance().delete(computer.getName());
    }
    
    this.getServletContext().getRequestDispatcher("/Dashboard").forward(request,
        response);
	}
	


}
