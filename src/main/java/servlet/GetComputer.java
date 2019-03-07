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

/**
 * Servlet implementation class GetComputer.
 */
@WebServlet("/GetComputer")
public class GetComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(GetComputer.class);

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
    Computer computer = new Computer();
    computer.setName(request.getParameter("search"));
    // TODO: get computers from companyName
    List<Computer> computers = serviceComputer.getComputerFromName(computer);
    List<ComputerDto> dto = Mapper.getInstance().listDtos(computers);

    request.setAttribute("computers", dto);

    request.setAttribute("maxId", dto.size());

    request.setAttribute("Order", "Ascend");
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
    doGet(request, response);
  }

}
