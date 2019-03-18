package main;

import config.SpringConfig;
import controller.ControllerCLI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
        new AnnotationConfigApplicationContext(SpringConfig.class);
    
    logger.debug("Starting program");
    
    ControllerCLI controller = applicationContext.getBean("controller", ControllerCLI.class);
    controller.start();
    
    ((ConfigurableApplicationContext)applicationContext).close();
  }

}
