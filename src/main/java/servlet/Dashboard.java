package servlet;

import dto.ComputerDto;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import mapper.Mapper;

import model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.ServiceComputer;

@Controller
public class Dashboard {
  private static final Logger logger = LoggerFactory.getLogger(Dashboard.class);

  private ServiceComputer serviceComputer;
  private Mapper mapper;
  
  /**
   * Instantiates a new dashboard.
   *
   * @param mapper the mapper
   * @param serviceComputer the service computer
   */
  @Autowired
  public Dashboard(Mapper mapper, ServiceComputer serviceComputer) {
    this.serviceComputer = serviceComputer;
    this.mapper = mapper;  
  }

  /**
   * Sets the dashboard.
   *
   * @param request the request
   * @param response the response
   * @return the model and view
   * @throws Exception the exception
   */
  @RequestMapping(value = "/Dashboard")
  public ModelAndView setDashboard(HttpServletRequest request) throws Exception {

    int page = setPage(request);
    int limit = setLimit(request);  
    
    List<Computer> computers = serviceComputer.listPage(limit, page);
    List<ComputerDto> dto = mapper.listDtos(computers);
    
    ModelAndView mv = new ModelAndView();   
    mv.addObject("page", page / 20);
    mv.addObject("maxId", serviceComputer.getMaxId()); 
    mv.addObject("limit", limit);
    mv.addObject("computers", dto);
    mv.addObject("Order", "ASC");
    mv.setViewName("dashboard");
    
    return mv;
  }
  
  /**
   * Do get.
   *
   * @param request the request
   * @return the model and view
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "/OrderBy")
  public ModelAndView setComputer(HttpServletRequest request)
      throws Exception {
    
    int page = setPage(request);
    int limit = setLimit(request);
    
    String order = request.getParameter("Order");
    String type = request.getParameter("type");
    
    List<Computer> computers = serviceComputer.orderBy(type, order, limit, page);
    List<ComputerDto> dto = mapper.listDtos(computers);

    if (order.equals("ASC")) {
      order = "DESC";
    } else {
      order = "ASC";
    }
    logger.debug("request is order by: " + order);
    ModelAndView mv = new ModelAndView(); 
    mv.addObject("computers", dto);
    mv.addObject("page", page / 20);
    mv.addObject("limit", limit);
    mv.addObject("maxId", serviceComputer.getMaxId());
    mv.addObject("Order", order);
    mv.setViewName("dashboard");
    return mv;
  }
  
  /**
   * Gets the computer.
   *
   * @param request the request
   * @param response the response
   * @return the computer
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "/GetComputer")
  public ModelAndView getComputer(HttpServletRequest request)
      throws Exception {

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
   * Do post.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "/DeleteComputer")
  public ModelAndView deleteComputer(HttpServletRequest request)
      throws Exception {
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
