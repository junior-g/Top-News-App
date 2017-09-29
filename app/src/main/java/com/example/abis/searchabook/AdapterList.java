package com.example.abis.searchabook;

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
 * Created by abis on 26/8/17.
 */

public class AdapterList extends ArrayAdapter<Books> {
    public AdapterList(Context context, List<Books> list)
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
       Books currentitem=getItem(position);
        TextView rating=(TextView)listView.findViewById(R.id.rating);
        TextView bookname=(TextView)listView.findViewById(R.id.book_name);
        TextView authors=(TextView)listView.findViewById(R.id.Authors);
        TextView discription=(TextView)listView.findViewById(R.id.discription);
        //for loading image
         imageView=(ImageView)listView.findViewById(R.id.bookimage);
        Double rate=currentitem.getRating();
        if(rate==0.0)
            rating.setText("NA");
        else
        rating.setText(" "+rate+" ");
        bookname.setText(currentitem.getBookname());
        authors.setText(currentitem.getAuthors());
        discription.setText(currentitem.getDiscription());

       loadImagefromURL(currentitem.getImageurl());
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
