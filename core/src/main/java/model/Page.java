package model;

public class Page {
  
  private int limit;
  private int offset;
  private String orderBy;
  private String type;
  private String search;
  
  public Page() {}

  public Page(int limit, int offset, String orderBy, String type, String search) {
    super();
    this.limit = limit;
    this.offset = offset;
    this.orderBy = orderBy;
    this.type = type;
    this.search = search;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }
  
  


}
