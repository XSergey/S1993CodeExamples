package com.vermilionsynergy.magadiskileton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    static ArrayList<MagadiListItem> items = new ArrayList<>();
    private itemListActivity.itemListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        items.add( new MagadiListItem("1", "Australian Broccoli", "Fresh Vegetables","6.55", ""));
        items.add( new MagadiListItem("2", "Givvo Local Tomatoes", "Fresh Vegetables","8.11", ""));
        items.add( new MagadiListItem("3", "Givvo Japanese Cucumbers", "Fresh Vegetables","3.26", ""));
        items.add( new MagadiListItem("4", "BELLVO Australian Carrots", "Fresh Vegetables","9.99", ""));
        items.add( new MagadiListItem("5", "Givvo Local Potatoes", "Fresh Vegetables","9.99", ""));


        ListView list = (ListView)findViewById(R.id.lvItemList);
        itemListAdapter mAdapter = new itemListAdapter(this, items);
        list.setAdapter(mAdapter);
    }
    public void backClick(View view){
        finish();
    }
    public void checkoutClick(View view){
        Intent intent = new Intent(CartActivity.this, Cart2Activity.class);
        startActivity(intent);
    }

    class itemListAdapter extends BaseAdapter {
        private ArrayList<MagadiListItem> mItems = new ArrayList<MagadiListItem>();
        private LayoutInflater layoutInflater;

        private itemListAdapter(Context context, ArrayList<MagadiListItem> mItems) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mItems = mItems;
        }

        public int getCount() {
            return mItems.size();
        }

        public MagadiListItem getItem(int position) {
            return mItems.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final MagadiListItem item = getItem(position);
            convertView = layoutInflater.inflate(R.layout.fragment_item_cart, null);
            View.OnClickListener ocl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), item.getId() + " Item open" ,Toast.LENGTH_SHORT).show();
                }
            };
            TextView tv = (TextView)convertView.findViewById(R.id.tvName);
            tv.setText(item.getName());
            tv.setOnClickListener(ocl);
            tv = (TextView)convertView.findViewById(R.id.tvCategory);
            tv.setOnClickListener(ocl);
            tv.setText(item.getCategory());
            tv = (TextView)convertView.findViewById(R.id.tvCost);
            tv.setOnClickListener(ocl);
            tv.setText("AED "+ item.getCost());

            return convertView;
        }
    }

}
