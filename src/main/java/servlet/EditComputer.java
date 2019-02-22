package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

/**
 * Servlet implementation class EditServlet.
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(EditComputer.class);

  /**
   * Do get.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Company> companies = ServiceCompany.getInstance().listCompany();
    logger.debug("Size of companies: " + companies.size());
    request.setAttribute("companies", companies);
    
    String StringId = request.getQueryString();
    int id = Integer.parseInt(StringId);  
    Computer computer = ServiceComputer.getInstance().getComputer(id).get(0);
    request.setAttribute("computer", computer);
    this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request,
        response);
  }

  /**
   * Do post.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Date dateDisc = null;
    Date dateIntro = null;

    String name = request.getParameter("name");

    String intro = request.getParameter("intro");
    dateIntro = setComputerIntro(intro);

    String disc = request.getParameter("disc");
    dateDisc = setComputerIntro(disc);//TODO: handle situation where disc>intro

    String companyIdString = request.getParameter("companyname");
    System.out.println("name:" + name + "Intro:" +intro + "disc: "+ disc +"ID: "+ companyIdString);
    Company company = ServiceCompany.getInstance().getCompany(companyIdString).get(0);//TODO: ça marche pas ici

    Computer computer = new Computer(name, company, dateIntro, dateDisc, 0);//TODO: get Id
    ServiceComputer.getInstance().update(computer);
    this.getServletContext().getRequestDispatcher("/Dashboard").forward(request,
        response);
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
      logger.error(e.getMessage(), e);
    }
    return null;
  }
  
  /**
   * Sets the computer intro.
   *
   * @param sc the scanner
   * @return the timestamp
   */
  public Date setComputerIntro(String disc) {
    Date intro = null;
    if (!disc.equals("")) {
      intro = setDate(disc);
    }
    logger.debug("Setting computer date of introduction: " + intro);
    return intro;
  }
  
  public Date setComputerDisc(Date intro, String disc) {//TODO: to change
    Date discontinuation = null;
    do {
      if (!disc.equals("")) {
        discontinuation = setDate(disc);
        if (null != intro) { // TODO: null.equals()null
          break;
        }
        if (discontinuation.before(intro)) {
          logger.info("The date you entered happened before the date of introduction. "
              + "Please enter a valid date.");
        }
      } else {
        break;
      }
    } while (null != intro || discontinuation.before(intro));
    logger.debug("Setting computer date of discontinuation: " + discontinuation);
    return discontinuation;

  }
}
