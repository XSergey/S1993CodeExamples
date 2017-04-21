package com.vermilionsynergy.magadiskileton;

import android.content.Context;
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

public class FaqListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_list);
        ListView listView = (ListView)findViewById(R.id.FaqList);
        ArrayList<Contact> items = new ArrayList<Contact>();
        TextView tw = (TextView)findViewById(R.id.titleText);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        tw.setTypeface(customFont);
        // Заполняем данными
        items.add(new Contact("1","Бонусная программа",""));
        items.add(new Contact("2","Как оформить заказ",""));
        items.add(new Contact("3","Доставка",""));
        items.add(new Contact("4","Способы оплаты",""));
        items.add(new Contact("5","Обмен и возврат товара",""));
        items.add(new Contact("6","Кредиты",""));
        items.add(new Contact("7","Услуги",""));
        items.add(new Contact("8","Информация для юр. лиц",""));
        items.add(new Contact("9","Сервисные центры",""));
        items.add(new Contact("10","Клуб экспертов",""));

        //String[] items = getResources().getStringArray(R.array.items);

        ListAdapter adapter = new SimpleAdapter(this, items, R.layout.faq_list_item,
                new String[] { Contact.NAME, Contact.ID}, new int[] {
                R.id.tvName, R.id.tvId});

        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.m_list_item, items);*/

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView)itemClicked.findViewById(R.id.tvId)).getText(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FaqListActivity.this, FaqArticleActivity.class);
                intent.putExtra("FAQ_id", ((TextView)itemClicked.findViewById(R.id.tvId)).getText());
                startActivity(intent);
            }
        });
    }
    public void backClick(View view){
        finish();
    }

    /*@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }*/
}
