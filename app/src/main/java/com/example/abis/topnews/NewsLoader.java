package com.example.abis.topnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by abis on 29/9/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {


    private static final String LOG_TAG = NewsLoader.class.getName();
    private String mUrl;

    public NewsLoader(Context context, String url)
    {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() { forceLoad();}

    @Override
    public List<News> loadInBackground() {
        if(mUrl==null)
            return null;
        List<News> books= jsonGenerator.extractBooks(mUrl);
        return books;
    }
}
