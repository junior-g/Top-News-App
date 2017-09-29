package com.example.abis.searchabook;

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
 * Created by abis on 27/8/17.
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
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
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
    public static List<Books> extractBooks(String urlString)
    {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Books> books=new ArrayList<>();
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
            JSONArray items= root.getJSONArray("items");
            for(int i=0; i<items.length(); ++i)
            {
                JSONObject book = items.getJSONObject(i);
                JSONObject volumeinfo=book.getJSONObject("volumeInfo");
                String title=volumeinfo.getString("title");
                StringBuilder aut=new StringBuilder();
                boolean isauthor=volumeinfo.isNull("authors");
                if(!isauthor) {
                    JSONArray authorlist = volumeinfo.getJSONArray("authors");
                    if(authorlist.length()>0)
                    {
                        String name = authorlist.getString(0);
                        aut.append(name);
                    }
                    for (int j = 1; j < authorlist.length(); ++j) {
                        String name = authorlist.getString(j);
                        aut.append(",");
                        aut.append(" ");
                        aut.append(name);
                    }
                }
                String authors=null;
                if(!isauthor)
                authors=aut.toString();

                boolean israting=volumeinfo.isNull("averageRating");
                Double rating=0.0;
                if(!israting)
               rating= volumeinfo.getDouble("averageRating");
                JSONObject accessinfo=book.getJSONObject("accessInfo");
                String urlLink=accessinfo.getString("webReaderLink");
                String discription="Publised in year- ";
                if(!volumeinfo.isNull("publishedDate"))
                discription+=volumeinfo.getString("publishedDate")+" "+"Language in-"+volumeinfo.getString("language");
                if(!volumeinfo.isNull("description"))
                    discription+="\n"+volumeinfo.getString("description");
                //image of book
                JSONObject imgobj=volumeinfo.getJSONObject("imageLinks");
                String imageSrc=imgobj.getString("thumbnail");
                books.add(new Books(title, authors, rating, urlLink, discription, imageSrc));
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
