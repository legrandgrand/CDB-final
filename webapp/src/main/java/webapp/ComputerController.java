package webapp;

import dto.ComputerDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mapper.DtoMapper;
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

import service.CompanyService;
import service.ComputerService;

@Controller
public class ComputerController {
  private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

  private ComputerService serviceComputer;
  private CompanyService serviceCompany;
  private DtoMapper mapper;

  /**
   * Instantiates a new computer controller.
   *
   * @param mapper          the mapper
   * @param serviceCompany  the service company
   * @param serviceComputer the service computer
   */
  @Autowired
  public ComputerController(DtoMapper mapper, CompanyService serviceCompany,
      ComputerService serviceComputer) {
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
    this.mapper = mapper;
  }

  /**
   * Sets the dashboard.
   *
   * @param pageString  the page string
   * @param limitString the limit string
   * @return the model and view
   */
  @GetMapping(value = "/Dashboard")
  public ModelAndView setDashboard(
      @RequestParam(defaultValue = "0", name = "page") String pageString,
      @RequestParam(defaultValue = "20", name = "limit") String limitString) {

    int page = setPage(pageString);
    int limit = setLimit(limitString);
    String order = "ASC";
    List<Computer> computers = serviceComputer.listPage(limit, page);
    return setMv(computers, order, page, limit);
  }

  /**
   * Sets the computer.
   *
   * @param pageString  the page string
   * @param limitString the limit string
   * @param order       the order
   * @param column      the column
   * @return the model and view
   */
  @GetMapping(value = "/OrderBy")
  public ModelAndView setComputer(
      @RequestParam(defaultValue = "0", name = "page") String pageString,
      @RequestParam(defaultValue = "20", name = "limit") String limitString,
      @RequestParam(defaultValue = "ASC", name = "Order") String order,
      @RequestParam(defaultValue = "id", name = "type") String column) {

    int page = setPage(pageString);
    int limit = setLimit(limitString);

    List<Computer> computers = serviceComputer.orderBy(column, order, limit, page);
    return setMv(computers, order, page, limit);
  }

  /**
   * Gets the computer.
   *
   * @param computerName the computer name
   * @return the computer
   */
  @GetMapping(value = "/GetComputer")
  public ModelAndView getComputer(@RequestParam(name = "search") String computerName) {

    Computer computer = new Computer();
    computer.setName(computerName);
    // TODO: get computers from companyName
    List<Computer> computers = serviceComputer.getComputerFromName(computer);
    List<ComputerDto> dto = mapper.listDtos(computers);

    ModelAndView mv = new ModelAndView("dashboard");
    mv.addObject("computers", dto);
    mv.addObject("maxId", dto.size());
    return mv;
  }

  /**
   * Delete computer.
   *
   * @param idString the id string
   * @return the model and view
   */
  @PostMapping(value = "/DeleteComputer")
  public ModelAndView deleteComputer(
      @RequestParam(required = false, name = "selection") String idString) {
    Computer computer = new Computer();

    if (idString != null) {
      String[] idStringTable = idString.split(",");

      for (String id : idStringTable) {
        computer.setId(Integer.parseInt(id));
        computer = serviceComputer.getComputer(computer).get(0);
        logger.debug("Deleting computer: " + computer.getName());
        serviceComputer.delete(computer);
      }
    }

    return setDashboard("0", "20");
  }

  /**
   * Sets the add Computer page.
   *
   * @return the model and view
   */
  @GetMapping(value = "/AddComputer")
  public ModelAndView getAdd() {

    List<Company> companies = serviceCompany.listCompany();

    ModelAndView mv = new ModelAndView();
    mv.addObject("companies", companies);
    mv.setViewName("addComputer");
    return mv;
  }

  /**
   * Post add.
   *
   * @param computerName the computer name
   * @param introString  the intro string
   * @param discString   the disc string
   * @param companyName  the company name
   * @return the model and view
   */
  @PostMapping(value = "/AddComputer")
  public ModelAndView postAdd(@RequestParam(name = "name") String computerName,
      @RequestParam(required = false, name = "intro") String introString,
      @RequestParam(required = false, name = "disc") String discString,
      @RequestParam(required = false, name = "companyname") String companyName) {

    Computer computer = setDto(computerName, introString, discString, companyName);

    logger.debug("Adding computer" + computer);
    serviceComputer.add(computer);

    return setDashboard("0", "20");
  }

  /**
   * Gets the edits the.
   *
   * @param stringId the string id
   * @return the edits the
   */
  @GetMapping(value = "/EditComputer")
  public ModelAndView getEdit(@RequestParam(name = "id") String stringId) {

    List<Company> companies = serviceCompany.listCompany();

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
   * Post edit.
   *
   * @param computerName the computer name
   * @param introString  the intro string
   * @param discString   the disc string
   * @param companyName  the company name
   * @return the model and view
   */
  @PostMapping(value = "/EditComputer")
  public ModelAndView postEdit(@RequestParam(name = "id") int id,
      @RequestParam(name = "name") String computerName,
      @RequestParam(required = false, name = "intro") String introString,
      @RequestParam(required = false, name = "disc") String discString,
      @RequestParam(required = false, name = "companyname") String companyName) {

    Computer computer = setDto(computerName, introString, discString, companyName);
    computer.setId(id);
    logger.debug("Updating computer" + computer);
    serviceComputer.update(computer);

    return setDashboard("0", "20");

  }

  /**
   * Sets the dto.
   *
   * @param computerName the computer name
   * @param introString  the intro string
   * @param discString   the disc string
   * @param companyName  the company name
   * @return the computer
   */
  public Computer setDto(String computerName, String introString, String discString,
      String companyName) {

    Company company = new Company();
    company.setName(companyName);
    company = serviceCompany.getCompany(company).get(0);

    ComputerDto dto = new ComputerDto();
    dto.setName(computerName);
    dto.setIntro(introString);
    dto.setDiscontinuation(discString);
    dto.setCompanyName(company.getName());
    dto.setIdCompany(company.getId());

    return mapper.dtoToComputer(dto);
  }

  /**
   * Sets the mv.
   *
   * @param computers the computers
   * @param order     the order
   * @param page      the page
   * @param limit     the limit
   * @return the model and view
   */
  public ModelAndView setMv(List<Computer> computers, String order, int page, int limit) {

    if (order.equals("ASC")) {
      order = "DESC";
    } else {
      order = "ASC";
    }

    List<ComputerDto> dto = mapper.listDtos(computers);

    ModelAndView mv = new ModelAndView("dashboard");
    mv.addObject("computers", dto);
    mv.addObject("page", page / 20);
    mv.addObject("limit", limit);
    mv.addObject("Order", order);
    mv.addObject("maxId", serviceComputer.getMaxId());
    return mv;
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
   * Sets the limit.
   *
   * @param limitString the limit string
   * @return the int
   */
  public int setLimit(String limitString) {
    int limit = 20;

    try {
      if (limitString != null) {
        limit = Integer.parseInt(limitString);
      }
    } catch (NumberFormatException se) {
      logger.error("PageString not valid");
    }

    return limit;
  }

  /**
   * Sets the page.
   *
   * @param pageString the page string
   * @return the int
   */
  public int setPage(String pageString) {
    int page = 0;

    try {
      if (pageString != null) {
        page = Integer.parseInt(pageString) * 20;
      }
    } catch (NumberFormatException e) {
      logger.error("PageString not valid");
    }

    return page;
  }

}
