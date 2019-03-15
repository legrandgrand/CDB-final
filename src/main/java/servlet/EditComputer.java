package servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Company;
import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
   * @param serviceCompany the service company
   * @param serviceComputer the service computer
   * @param dashboard the dashboard
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
   * @param stringId the string id
   * @return the model and view
   */
  @GetMapping(value = "/EditComputer")
  public ModelAndView doGet(@RequestParam(name = "id") String stringId) {
    List<Company> companies = serviceCompany.listCompany();
    logger.debug("Size of companies: " + companies.size());

    Computer computer = new Computer();
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
   * @param computerName the computer name
   * @param introString the intro string
   * @param discString the disc string
   * @param companyName the company name
   * @return the model and view
   */
  @PostMapping(value = "/EditComputer")
  public ModelAndView doPost(@RequestParam(name = "name") String computerName,
      @RequestParam(required = false, name = "intro") String introString,
      @RequestParam(required = false, name = "disc") String discString,
      @RequestParam(required = false, name = "companyname") String companyName) {
    Computer computer = new Computer();
    computer.setName(computerName);

    Date intro = setComputerDate(introString);
    computer.setDateIntro(intro);

    computer.setDateDiscontinuation(setComputerDate(discString));

    Company company = new Company();
    company.setName(companyName);

    company = serviceCompany.getCompany(company).get(0);

    computer.setCompany(company);
    logger.debug("Updating computer" + computer);
    serviceComputer.update(computer);

    return dashboard.setDashboard("0", "20");

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
   * Sets the computer date.
   *
   * @param stringDate the string date
   * @return the date
   */
  public Date setComputerDate(String stringDate) {
    Date intro = null;
    if (stringDate != null) {
      intro = setDate(stringDate);
    }
    logger.debug("Setting computer date of introduction: " + intro);
    return intro;
  }
}
