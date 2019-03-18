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

@Controller
public class AddComputer {
  private static final Logger logger = LoggerFactory.getLogger(AddComputer.class);

  private ServiceComputer serviceComputer;
  private ServiceCompany serviceCompany;
  private Dashboard dashboard;

  /**
   * Instantiates a new adds the computer.
   *
   * @param serviceCompany the service company
   * @param serviceComputer the service computer
   * @param dashboard the dashboard
   */
  @Autowired
  public AddComputer(ServiceCompany serviceCompany, ServiceComputer serviceComputer,
      Dashboard dashboard) {
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
    this.dashboard = dashboard;
  }

  /**
   * Sets the add Computer page.
   *
   * @return the model and view
   */
  @GetMapping(value = "/AddComputer")
  public ModelAndView setAdd() {

    List<Company> companies = serviceCompany.listCompany();
    logger.debug("Size of companies: " + companies.size());

    ModelAndView mv = new ModelAndView();
    mv.addObject("companies", companies);
    mv.setViewName("addComputer");
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
  @PostMapping(value = "/AddComputer")
  public ModelAndView doPost(@RequestParam(name = "name") String computerName,
      @RequestParam(required = false, name = "intro") String introString,
      @RequestParam(required = false, name = "disc") String discString,
      @RequestParam(required = false, name = "companyname") String companyName) {

    Computer computer = new Computer();
    computer.setName(computerName);

    Date intro = setComputerIntro(introString);
    computer.setIntro(intro);

    computer.setDiscontinuation(setComputerDisc(intro, discString));

    Company company = new Company();
    company.setName(companyName);
    company = serviceCompany.getCompany(company).get(0);
    computer.setCompany(company);

    logger.debug("Adding computer" + computer);
    serviceComputer.add(computer);

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
   * Sets the computer intro.
   *
   * @param introString the intro string
   * @return the date
   */
  public Date setComputerIntro(String introString) {
    Date intro = null;
    if (!introString.equals("")) {
      intro = setDate(introString);
    }
    logger.debug("Setting computer date of introduction: " + intro);
    return intro;
  }

  /**
   * Sets the computer disc.
   *
   * @param intro the intro
   * @param disc the disc
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
