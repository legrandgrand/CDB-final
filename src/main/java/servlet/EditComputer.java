package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.ServiceCompany;
import service.ServiceComputer;

/**
 * Servlet implementation class EditServlet.
 */
@WebServlet("/EditComputer")
@Configurable
public class EditComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(EditComputer.class);
  
  @Autowired
  private ServiceComputer serviceComputer;
  
  @Autowired
  private ServiceCompany serviceCompany;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Company> companies = serviceCompany.listCompany();
    logger.debug("Size of companies: " + companies.size());
    request.setAttribute("companies", companies);

    Computer computer = new Computer();
    String stringId = request.getQueryString();
    computer.setId(Integer.parseInt(stringId));
    
    computer = serviceComputer.getComputer(computer).get(0);
    request.setAttribute("computer", computer);

    this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request,
        response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Computer computer = new Computer();
    computer.setName(request.getParameter("name"));

    String introString = request.getParameter("intro");
    Date intro = setComputerIntro(introString);
    computer.setDateIntro(intro);

    String disc = request.getParameter("disc"); // TODO: handle situation where disc>intro
    computer.setDateDiscontinuation(setComputerDisc(intro, disc));
    
    Company company = new Company();
    company.setName(request.getParameter("companyname"));

    company = serviceCompany.getCompany(company).get(0);

    computer.setCompany(company);
    logger.debug("Updating computer" + computer);
    serviceComputer.update(computer);
    response.sendRedirect(request.getContextPath() + "/Dashboard");
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
   * @param disc the disc
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

  /**
   * Sets the computer disc.
   *
   * @param intro the intro
   * @param disc  the disc
   * @return the date
   */
  public Date setComputerDisc(Date intro, String disc) { // TODO: to change
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
