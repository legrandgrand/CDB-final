package servlet;

import dto.ComputerDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

  @Autowired
  private ServiceComputer serviceComputer;
  
  @Autowired
  private Mapper mapper;

  @RequestMapping(value = "/Dashboard")
  protected ModelAndView doGet(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

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
