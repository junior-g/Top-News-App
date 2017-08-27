package com.example.abis.searchabook;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.LoaderManager.LoaderCallbacks;

import java.util.ArrayList;
import java.util.List;

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
    public static String url="https://www.googleapis.com/books/v1/volumes?q="+"gitanjali";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=(EditText)findViewById(R.id.search_keyword);
        ///generating URL
        Button searchButton=(Button)findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlResult=new StringBuilder();
                urlResult.append("https://www.googleapis.com/books/v1/volumes?q=");
                searchKeyword=searchView.getText().toString();

                for(int i=0; i<searchKeyword.length(); ++i)
        {
            if(searchKeyword.charAt(i)==' ')
            {
                urlResult.append("%20");
            }
            else
            {
                urlResult.append(searchKeyword.charAt(i));
            }
        }

                searchView.setVisibility(View.GONE);
        TextView textView=(TextView)findViewById(R.id.searchResult);
         url=urlResult.toString();
        textView.setText("search result for "+searchKeyword+"\n   URL="+url);
        Log.e("URL =", url);
        Button searchButton=(Button)findViewById(R.id.search_button);
        searchButton.setVisibility(View.GONE);

                Intent i=new Intent(MainActivity.this, BooksListing.class);
                startActivity(i);
            }
        });


    }
}
