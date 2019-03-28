package webapp;

import dto.CompanyDto;
import dto.ComputerDto;
import exception.ComputerValidationException;
import model.Page;

import java.util.ArrayList;
import java.util.List;

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

  @Autowired
  public ComputerController(CompanyService serviceCompany, ComputerService serviceComputer) {
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
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

    Page page = new Page(setLimit(limitString), setPage(pageString), "ASC", "", "");
    List<ComputerDto> dtos = serviceComputer.listPage(page);
    return setMv(dtos, page.getOrderBy(), page.getOffset(), page.getLimit());
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

    Page page = new Page(setLimit(limitString), setPage(pageString), order, column, "");

    List<ComputerDto> dtos = serviceComputer.orderBy(page);
    return setMv(dtos, page.getOrderBy(), page.getOffset(), page.getLimit());
  }

  /**
   * Gets the computer.
   *
   * @param computerName the computer name
   * @return the computer
   */
  @GetMapping(value = "/GetComputer")
  public ModelAndView getComputer(@RequestParam(name = "search") String computerName) {

    ComputerDto dto = new ComputerDto();
    List<ComputerDto> dtos = new ArrayList<>();
    ModelAndView mv = new ModelAndView("dashboard");
    
    dto.setName(computerName);
    // TODO: get computers from companyName
    
    try {
      dtos = serviceComputer.getFromName(dto);
    } catch (ComputerValidationException invalidComputer) {
      logger.error(invalidComputer.getMessage(), invalidComputer);
      mv.addObject("message", invalidComputer.getMessage());//TODO: Send error message
    }

    mv.addObject("computers", dtos);
    mv.addObject("maxId", dtos.size());
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
    ComputerDto dto = new ComputerDto();

    if (idString != null) {
      String[] idStringTable = idString.split(",");

      for (String id : idStringTable) {
        dto = serviceComputer.getFromId(Integer.parseInt(id)).get(0);
        
        try {
          serviceComputer.delete(dto);
        } catch (ComputerValidationException invalidComputer) {
          logger.error(invalidComputer.getMessage(), invalidComputer);
        }
        
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

    List<CompanyDto> companies = serviceCompany.listCompany();

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

    ComputerDto Dto = setDto(computerName, introString, discString, companyName);

    try {
      serviceComputer.add(Dto);
    } catch (ComputerValidationException invalidComputer) {
      logger.error(invalidComputer.getMessage(), invalidComputer);//TODO: show message, return to add computer
    }

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

    List<CompanyDto> companies = serviceCompany.listCompany();

    ComputerDto dto = serviceComputer.getFromId(Integer.parseInt(stringId)).get(0);

    ModelAndView mv = new ModelAndView();
    mv.addObject("companies", companies);
    mv.addObject("computer", dto);
    mv.setViewName("editComputer");
    return mv;

  }

  /**
   * Post edit.
   *
   * @param id           the id
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

    ComputerDto dto = setDto(computerName, introString, discString, companyName);
    dto.setIdComputer(id);
    try {
      serviceComputer.update(dto);
    } catch (ComputerValidationException invalidComputer) {
      logger.error(invalidComputer.getMessage(), invalidComputer);//TODO: show message user and stay on EditComputer
    }

    return setDashboard("0", "20");

  }

  private ComputerDto setDto(String computerName, String introString, String discString,
      String companyName) {

    CompanyDto company = new CompanyDto();
    company.setName(companyName);
    company = serviceCompany.getCompany(company).get(0);

    ComputerDto dto = new ComputerDto();
    dto.setName(computerName);
    dto.setIntro(introString);
    dto.setDiscontinuation(discString);
    dto.setCompanyName(company.getName());
    dto.setIdCompany(company.getId());

    return dto;
  }

  private ModelAndView setMv(List<ComputerDto> dto, String order, int page, int limit) {

    if (order.equals("ASC")) {
      order = "DESC";
    } else {
      order = "ASC";
    }

    ModelAndView mv = new ModelAndView("dashboard");
    mv.addObject("computers", dto);
    mv.addObject("page", page / 20);
    mv.addObject("limit", limit);
    mv.addObject("Order", order);
    mv.addObject("maxId", serviceComputer.getMaxId());
    return mv;
  }

  private int setLimit(String limitString) {
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

  private int setPage(String pageString) {
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
