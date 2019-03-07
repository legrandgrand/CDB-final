package dao;

public class DaoFactory {

  private static final DaoFactory instance = new DaoFactory();

  /**
   * Get instance of a new dao factory.
   */
  public static final DaoFactory getInstance() {
    return instance;
  }

  private DaoFactory() {
  }


}
