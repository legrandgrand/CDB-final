package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ServiceCompany;
import service.ServiceComputer;

@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(AddComputer.class);

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Company> companies = ServiceCompany.getInstance().listCompany();
    logger.debug("Size of companies: " + companies.size());
    request.setAttribute("companies", companies);
    this.getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request,
        response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Date dateDisc = null;
    Date dateIntro = null;

    String name = request.getParameter("name");

    String intro = request.getParameter("intro");
    dateIntro = setDate(intro);

    String disc = request.getParameter("disc");
    dateDisc = setDate(disc);

    String companyIdString = request.getParameter("companyname");
    Company company = ServiceCompany.getInstance().getCompany(companyIdString).get(0);

    Computer computer = new Computer(name, company.getCompanyId(), dateIntro, dateDisc);
    ServiceComputer.getInstance().add(computer);
    doGet(request, response);
  }

  /**
   * Sets the timestamp.
   *
   * @param timestamp the timestamp to change
   * @return the timestamp
   */
  public Date setDate(String timestamp) {
    timestamp = timestamp + " 00:00:00";// timestamp format: YYYY-MM-DD (user input) + 00:00:00
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    try {
      return dt.parse(timestamp);
    } catch (ParseException e) {
      e.printStackTrace();
      logger.error("Parse Exception");
    }
    return null;
  }
}
