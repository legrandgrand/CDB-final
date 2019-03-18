package servlet;

import dto.ComputerDto;

import java.util.List;

import mapper.DtoMapper;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.ServiceComputer;

@Controller
public class Dashboard {
  private static final Logger logger = LoggerFactory.getLogger(Dashboard.class);

  private ServiceComputer serviceComputer;
  private DtoMapper mapper;

  /**
   * Instantiates a new dashboard.
   *
   * @param mapper          the mapper
   * @param serviceComputer the service computer
   */
  @Autowired
  public Dashboard(DtoMapper mapper, ServiceComputer serviceComputer) {
    this.serviceComputer = serviceComputer;
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
   * @param type        the type
   * @return the model and view
   */
  @GetMapping(value = "/OrderBy")
  public ModelAndView setComputer(
      @RequestParam(defaultValue = "0", name = "page") String pageString,
      @RequestParam(defaultValue = "20", name = "limit") String limitString,
      @RequestParam(defaultValue = "ASC", name = "Order") String order,
      @RequestParam(defaultValue = "id", name = "type") String type) {

    int page = setPage(pageString);
    int limit = setLimit(limitString);

    List<Computer> computers = serviceComputer.orderBy(type, order, limit, page);
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
  @GetMapping(value = "/DeleteComputer")
  public ModelAndView deleteComputer(
      @RequestParam(required = false, name = "selection") String idString) {
    Computer computer = new Computer();

    if (idString != null) {
      String[] idStringTable = idString.split(",");

      for (String c : idStringTable) {
        computer.setId(Integer.parseInt(c));
        computer = serviceComputer.getComputer(computer).get(0);
        logger.debug("Deleting computer: " + computer.getName());
        serviceComputer.delete(computer);
      }
    }

    return new ModelAndView("dashboard");
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
    mv.addObject("page", page / 20);
    mv.addObject("maxId", serviceComputer.getMaxId());
    mv.addObject("limit", limit);
    mv.addObject("computers", dto);
    mv.addObject("Order", order);
    return mv;
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
