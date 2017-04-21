package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

public class itemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ArrayList<DataItem> items = new ArrayList<>();

        items.add(new DataItem("1","RedMart Multigrain Bread Sliced","by Canned Food","100", "2.53", "3"));
        items.add(new DataItem("2","RedMart Freshly Made Wholemeal Flatbread","by Canned Food","110", "1.12", "5"));
        items.add(new DataItem("3","RedMart Freshly Made Wholemeal Wraps","by Canned Food","200", "3.00", "3"));
        items.add(new DataItem("4","RedMart German Multigrain Roll 6s","by Canned Food","230", "0.50", "4"));
        items.add(new DataItem("5","RedMart Swiss Country Bread","by Canned Food","180", "6.80", "1"));
        items.add(new DataItem("6","RedMart Sesame Hamburger Buns 4s","by Canned Food","50", "6.30", "0"));
        items.add(new DataItem("7","RedMart Focaccia With Herbs","by Canned Food","520", "2.22", "2"));
        String id = "4";
        Integer iid = 4;
        try{
            id = getIntent().getStringExtra("com.vermilionsynergy.magadiskileton.item_id").toString();
            iid = Integer.parseInt(id);
        } catch (Exception e){}

        for(int i = 0; i < items.size(); i++) {
            /*Log.d("check", items.get(i).get("id").toString() + "/" + iid + " = " + (items.get(i).get("id").toString() == iid.toString() ? "true" : "false"));*/
            if(items.get(i).get("id").toString() == iid.toString()){
                DataItem di = items.get(i);
                TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
                TextView tvSection = (TextView)findViewById(R.id.tvSection);
                TextView tvCost = (TextView)findViewById(R.id.tvCost);
                TextView tvWeight = (TextView)findViewById(R.id.tvWeight);
                ImageView ivRating = (ImageView)findViewById(R.id.ivRating);
                ImageView ivItemImg = (ImageView)findViewById(R.id.ivItemImg);
                tvTitle.setText(di.get("title"));
                tvSection.setText(di.get("section"));
                tvCost.setText(di.get("cost")+ " AED");
                tvWeight.setText(di.get("weight") + "g");
                switch (di.get("rating")){
                    case "1":
                        ivRating.setImageResource(R.drawable.stars1);
                        break;
                    case "2":
                        ivRating.setImageResource(R.drawable.stars2);
                        break;
                    case "3":
                        ivRating.setImageResource(R.drawable.stars3);
                        break;
                    case "4":
                        ivRating.setImageResource(R.drawable.stars4);
                        break;
                    case "5":
                        ivRating.setImageResource(R.drawable.stars5);
                        break;
                    default:
                        ivRating.setImageResource(R.drawable.stars0);
                        break;
                }
                switch (di.get("id")) {
                    case "1":
                        ivItemImg.setImageResource(R.drawable.it1);
                        break;
                    case "2":
                        ivItemImg.setImageResource(R.drawable.it2);
                        break;
                    case "3":
                        ivItemImg.setImageResource(R.drawable.it3);
                        break;
                    case "4":
                        ivItemImg.setImageResource(R.drawable.it4);
                        break;
                    case "5":
                        ivItemImg.setImageResource(R.drawable.it5);
                        break;
                    case "6":
                        ivItemImg.setImageResource(R.drawable.it6);
                        break;
                    case "7":
                        ivItemImg.setImageResource(R.drawable.it7);
                        break;
                    default:
                        ivItemImg.setImageResource(R.drawable.it1);
                        break;
                }

            }
        }
    }
    public void backClick(View view){
        finish();
    }
    public void cartClick(View view){
        //Intent intent = new Intent(this, CartActivity.class);
        //startActivity(intent);
    }
    public void searchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void goAddToCart(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "item added to cart", Toast.LENGTH_SHORT);
        toast.show();
    }
}
