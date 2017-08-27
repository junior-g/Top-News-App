package com.example.abis.searchabook;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by abis on 27/8/17.
 */

public class BookLoader extends AsyncTaskLoader<List<Books>> {


    private static final String LOG_TAG = BookLoader.class.getName();
    private String mUrl;

    public  BookLoader(Context context, String url)
    {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() { forceLoad();}

    @Override
    public List<Books> loadInBackground() {
        if(mUrl==null)
            return null;
        List<Books> books= jsonGenerator.extractBooks(mUrl);
        return books;
    }
}
