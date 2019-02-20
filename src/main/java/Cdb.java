import controller.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ServiceCompany;
import service.ServiceComputer;

import view.View;

public class Cdb {

  private static final Logger logger = LoggerFactory.getLogger(Cdb.class);

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    logger.debug("Starting program");
    ServiceComputer s1 = ServiceComputer.getInstance();
    ServiceCompany s2 = ServiceCompany.getInstance();
    Controller c = new Controller(s1, s2);
    View view = new View(c);
  }

}
