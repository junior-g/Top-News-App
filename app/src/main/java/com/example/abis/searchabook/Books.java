package com.example.abis.searchabook;

/**
 * Created by abis on 26/8/17.
 */

public class Books {
  private   String bookname;
  private   String authors;
  private   Double rating;
 private    String url;
    private String discription;
  public Books(String b, String a, Double r, String u, String d)
    {
        bookname=b;
        authors=a;
        rating=r;
        url=u;
        discription=d;
    }
    public String getBookname(){ return bookname;}
    public String getAuthors() { return  authors;}
    public Double getRating() { return rating;}
    public String getUrl() { return url;}
    public String getDiscription() { return  discription;}

}
