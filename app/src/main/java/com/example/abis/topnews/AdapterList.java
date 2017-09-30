package com.example.abis.topnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abis on 29/9/17.
 */

public class AdapterList extends ArrayAdapter<News> {
    public AdapterList(Context context, List<News> list)
    {
        super(context, 0, list);
    }
    ImageView imageView;
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {


        View listView =convertView;
        if(listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_arrangment, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
       News currentitem=getItem(position);
        TextView title=(TextView)listView.findViewById(R.id.headline);
        TextView discription=(TextView)listView.findViewById(R.id.discription);
        //for loading image
         imageView=(ImageView)listView.findViewById(R.id.caption_image);
        title.setText(currentitem.getTitle());
        discription.setText(currentitem.getDiscription());

       loadImagefromURL(currentitem.getImg());
        return listView;
    }
    private void loadImagefromURL(String url)
    {
        Picasso.with(getContext()).load(url).placeholder(R.mipmap.ic_launcher)  //optional
        .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
