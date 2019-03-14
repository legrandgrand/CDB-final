package servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import service.ServiceCompany;
import service.ServiceComputer;

/**
 * Servlet implementation class EditServlet.
 */
@Controller
public class EditComputer {
  private static final Logger logger = LoggerFactory.getLogger(EditComputer.class);

  private ServiceComputer serviceComputer;
  private ServiceCompany serviceCompany;
  private Dashboard dashboard;

  /**
   * Instantiates a new edits the computer.
   *
   * @param serviceCompany  the service company
   * @param serviceComputer the service computer
   * @param dashboard       the dashboard
   */
  @Autowired
  public EditComputer(ServiceCompany serviceCompany, ServiceComputer serviceComputer,
      Dashboard dashboard) {
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
    this.dashboard = dashboard;
  }

  /**
   * Do get.
   *
   * @param request  the request
   * @return the model and view
   * @throws Exception the exception
   */
  @GetMapping(value = "/EditComputer")
  public ModelAndView doGet(HttpServletRequest request) {
    List<Company> companies = serviceCompany.listCompany();
    logger.debug("Size of companies: " + companies.size());

    Computer computer = new Computer();
    String stringId = request.getQueryString();
    computer.setId(Integer.parseInt(stringId));
    computer = serviceComputer.getComputer(computer).get(0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("companies", companies);
    mv.addObject("computer", computer);
    mv.setViewName("editComputer");
    return mv;

  }

  /**
   * Do post.
   *
   * @param request  the request
   * @return the model and view
   * @throws Exception the exception
   */
  @PostMapping(value = "/EditComputer")
  public ModelAndView doPost(HttpServletRequest request) {
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

    return dashboard.setDashboard(request);

  }

  /**
   * Sets the date.
   *
   * @param timestamp the timestamp
   * @return the date
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
   * @return the date
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
