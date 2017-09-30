package com.example.abis.topnews;

/**
 * Created by abis on 29/9/17.
 */

public class News {
    private String title, discription, url1, img;
    public News(String t, String d, String u, String i)
    {
        title=t;
        discription=d;
        url1=u;
        img=i;
    }
    public String getTitle(){ return title;}
    public String getDiscription() { return discription;}
    public String getUrl(){ return url1;}
    public String getImg(){ return img;}
}
