package com.example.abis.topnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private  static  final  int LOADER_ID=1;
    private AdapterList mAdapter;
    //to store search keyword
    //JSON
    public static String  tempURL="https://www.googleapis.com/books/v1/volumes?q=http://books.google.com/books/content?id=e0b5wUwNGt4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
   public EditText searchView;
  public  static    String searchKeyword;
   //Search URL @TODO:  https://www.googleapis.com/books/v1/volumes?q=
    ///+keyword or key word=key%20word
   private StringBuilder urlResult;
    public static String url="https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=ea14a44e20cd4a72814330a6c6300acd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bbc news
        ImageView bcc_news_image=(ImageView)findViewById(R.id.bcc_news);
        bcc_news_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // url=null;
                url="https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=ea14a44e20cd4a72814330a6c6300acd";
                Intent i=new Intent(MainActivity.this, NewsListing.class);
                startActivity(i);
            }
        });
        //daily mail
        ImageView daily_mail=(ImageView)findViewById(R.id.daily_mail);
        daily_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // url=null;
                url="https://newsapi.org/v1/articles?source=daily-mail&sortBy=top&apiKey=ea14a44e20cd4a72814330a6c6300acd";
                Intent i=new Intent(MainActivity.this, NewsListing.class);
                startActivity(i);
            }
        });
        //cnn news
        ImageView cnn=(ImageView)findViewById(R.id.cnn);
        cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // url=null;
                url="https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=ea14a44e20cd4a72814330a6c6300acd";
                Intent i=new Intent(MainActivity.this, NewsListing.class);
                startActivity(i);
            }
        });

        //espn cric info
        ImageView espn_cric_info=(ImageView)findViewById(R.id.espn_cric_info);
        espn_cric_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // url=null;
                url="https://newsapi.org/v1/articles?source=espn-cric-info&sortBy=top&apiKey=ea14a44e20cd4a72814330a6c6300acd";
                Intent i=new Intent(MainActivity.this, NewsListing.class);
                startActivity(i);
            }
        });
        //finance times
        ImageView ft=(ImageView)findViewById(R.id.ft);
        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // url=null;
                url="https://newsapi.org/v1/articles?source=financial-times&sortBy=top&apiKey=ea14a44e20cd4a72814330a6c6300acd";
                Intent i=new Intent(MainActivity.this, NewsListing.class);
                startActivity(i);
            }
        });
    }
}
