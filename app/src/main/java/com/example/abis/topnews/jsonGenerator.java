package com.example.abis.topnews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abis on 29/9/17.
 */

public final class jsonGenerator {
    private jsonGenerator(){   }

    private static String LOG_TAG=MainActivity.class.getName();

    //coverting string url to original URL
    private static URL createURL(String st)
    {
        URL url=null;
        try
        {
            url= new URL(st);
        }catch (MalformedURLException e)
        {
            Log.e(LOG_TAG, "Error with creating URL ", e);
            return null;
        }return url;
    }
    //read FROM input

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private  static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    //HTTP request and get JSON
    private static String makeHttpRequest(URL url) throws IOException
    {
        String jsonResponse="";
        if(url==null)
            return jsonResponse;
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try
        {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(100000 /* milliseconds */);
            urlConnection.setConnectTimeout(150000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }



    //geting list
    public static List<News> extractBooks(String urlString)
    {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<News> books=new ArrayList<>();
        URL url=createURL(urlString);
        String JSON_RESPONSE="";
        try {
            JSON_RESPONSE = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        //TRY TO PARSING THE JSON_RESPONSE
        try
        {
            JSONObject root=new JSONObject(JSON_RESPONSE);
            JSONArray items= root.getJSONArray("articles");
            for(int i=0; i<items.length(); ++i)
            {
                JSONObject news = items.getJSONObject(i);
                String title = "<NO TITLE>", discription="<NO DISCRIPTION>", urltoImage="http://epaper2.mid-day.com/images/no_image_thumb.gif";
                String urlto="https://www.startalkradio.net/wp-content/uploads/2016/02/no-link-300x300.jpg";
                if(!news.isNull("title"))
                 title=news.getString("title");
                if(!news.isNull("description"))
                 discription=news.getString("description");
                if(!news.isNull("url"))
                 urlto=news.getString("url");
                if(!news.isNull("urlToImage"))
                 urltoImage=news.getString("urlToImage");
                books.add(new News(title, discription, urlto, urltoImage));
            }
        }catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return books;
    }

}
