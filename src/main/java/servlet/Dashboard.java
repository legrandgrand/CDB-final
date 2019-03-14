package servlet;

import dto.ComputerDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mapper.DtoMapper;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
   * @param mapper the mapper
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
   * @param request the request
   * @return the model and view
   */
  @GetMapping(value = "/Dashboard")
  public ModelAndView setDashboard(HttpServletRequest request) {

    int page = setPage(request);
    int limit = setLimit(request);
    String order = "ASC";
    List<Computer> computers = serviceComputer.listPage(limit, page);

    return setMv(computers, order, page, limit);
  }

  /**
   * Sets the computer.
   *
   * @param request the request
   * @return the model and view
   */
  @GetMapping(value = "/OrderBy")
  public ModelAndView setComputer(HttpServletRequest request) {

    int page = setPage(request);
    int limit = setLimit(request);

    String order = request.getParameter("Order");
    String type = request.getParameter("type");

    List<Computer> computers = serviceComputer.orderBy(type, order, limit, page);
    return setMv(computers, order, page, limit);
  }

  /**
   * Gets the computer.
   *
   * @param request the request
   * @return the computer
   */
  @GetMapping(value = "/GetComputer")
  public ModelAndView getComputer(HttpServletRequest request) {

    Computer computer = new Computer();
    computer.setName(request.getParameter("search"));
    // TODO: get computers from companyName
    List<Computer> computers = serviceComputer.getComputerFromName(computer);
    List<ComputerDto> dto = mapper.listDtos(computers);

    ModelAndView mv = new ModelAndView();
    mv.addObject("computers", dto);
    mv.addObject("maxId", dto.size());
    mv.setViewName("dashboard");
    return mv;
  }

  /**
   * Delete computer.
   *
   * @param request the request
   * @return the model and view
   */
  @GetMapping(value = "/DeleteComputer")
  public ModelAndView deleteComputer(HttpServletRequest request) {
    Computer computer = new Computer();
    String idString = request.getParameter("selection");
    String[] idStringTable = idString.split(",");
    
    for (String c : idStringTable) {
      computer.setId(Integer.parseInt(c));
      computer = serviceComputer.getComputer(computer).get(0);
      logger.debug("Deleting computer: " + computer.getName());
      serviceComputer.delete(computer);
    }

    return new ModelAndView("dashboard");
  }

  /**
   * Sets the ModelAndView.
   *
   * @param computers the computers
   * @param order the order
   * @param page the page
   * @param limit the limit
   * @return the model and view
   */
  public ModelAndView setMv(List<Computer> computers, String order, int page, int limit) {

    if (order.equals("ASC")) {
      order = "DESC";
    } else {
      order = "ASC";
    }
    
    List<ComputerDto> dto = mapper.listDtos(computers);
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("page", page / 20);
    mv.addObject("maxId", serviceComputer.getMaxId());
    mv.addObject("limit", limit);
    mv.addObject("computers", dto);
    mv.addObject("Order", order);
    mv.setViewName("dashboard");

    return mv;
  }

  /**
   * Sets the limit.
   *
   * @param request the request
   * @return the int
   */
  public int setLimit(HttpServletRequest request) {
    String limitString = null;
    int limit = 20;
    limitString = request.getParameter("limit");

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
   * @param request the request
   * @return the int
   */
  public int setPage(HttpServletRequest request) {
    String pageString = null;
    int page = 0;
    pageString = request.getParameter("page");

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
