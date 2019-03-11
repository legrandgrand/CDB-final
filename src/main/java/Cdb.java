import config.SpringConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import view.View;

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
    
    View view = View.class.cast(applicationContext.getBean("view", View.class));
    view.start();
  }

}
