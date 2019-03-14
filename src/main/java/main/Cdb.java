package main;

import config.SpringConfigCli;
import controller.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cdb {

  private static final Logger logger = LoggerFactory.getLogger(Cdb.class);

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(SpringConfigCli.class);
    
    logger.debug("Starting program");
    
    Controller controller = applicationContext.getBean("controller", Controller.class);
    controller.start();
  }

}
