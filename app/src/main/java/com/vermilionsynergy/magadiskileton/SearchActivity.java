package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView listView = (ListView)findViewById(R.id.searchList);
        ArrayList<SearchItem> items = new ArrayList<>();
        TextView tw = (TextView)findViewById(R.id.titleText);
        /*Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        tw.setTypeface(customFont);*/
        // Заполняем данными
        items.add(new SearchItem("1","Salt","in subcategory"));
        items.add(new SearchItem("2","Seasalt","in subcategory"));
        items.add(new SearchItem("3","Rins aid & Salt Company","in subcategory"));
        items.add(new SearchItem("4","Sun Francisco Salt Company","in subcategory"));
        items.add(new SearchItem("5","Saxa Pink Himalayan Salt Grinder","in subcategory"));
        items.add(new SearchItem("6","Pagoda Fine Salt","in subcategory"));
        items.add(new SearchItem("7","Уsuer sea salt","in subcategory"));

        ListAdapter adapter = new SimpleAdapter(this, items, R.layout.search_list_item,
                new String[] { SearchItem.NAME, SearchItem.ID, SearchItem.SECTION, SearchItem.SECTION}, new int[] {
                R.id.tvName, R.id.tvId, R.id.tvSection});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView)itemClicked.findViewById(R.id.tvId)).getText(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, itemActivity.class);
                intent.putExtra("item_id", ((TextView)itemClicked.findViewById(R.id.tvId)).getText());
                startActivity(intent);
            }
        });
    }
    public void backClick(View view){
        finish();
    }
}
