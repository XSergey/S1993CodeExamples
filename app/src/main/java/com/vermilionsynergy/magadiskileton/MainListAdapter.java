package com.vermilionsynergy.magadiskileton;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public MainListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.main_list_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.main_list_item, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.mainListImage);

        imageView.setImageResource(imgid[position]);
        //imageView.setImageResource(R.drawable.main1);
        return rowView;

    };
}
