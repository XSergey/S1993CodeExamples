package com.vermilionsynergy.magadiskileton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class itemListActivity extends AppCompatActivity {
    static ArrayList<MagadiListItem> items = new ArrayList<>();
    private itemListAdapter mAdapter;
    static jsonData jd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Integer section_iid = 4;
        String section_id = "0";
        String section_name = "no category";
        try{
            section_id = getIntent().getStringExtra("com.vermilionsynergy.magadiskileton.list_id").toString();
            section_name = getIntent().getStringExtra("com.vermilionsynergy.magadiskileton.listname").toString();
            section_iid = Integer.parseInt(section_id);
        } catch (Exception e){}

        ListView list = (ListView)findViewById(R.id.lvItemList);
        mAdapter = new itemListAdapter(this, items);
        list.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(itemListActivity.this);

        View headerLayout = navigationView.getHeaderView(0);

        WebView wv = (WebView)headerLayout.findViewById(R.id.menuWV);
        wv.setBackgroundColor(Color.WHITE);
        wv.getSettings().setJavaScriptEnabled(true);
        //wv.addJavascriptInterface(new Main2Activity.MyJavaInterface(), "app");
        wv.loadUrl("file:///android_asset/menu.html");
        jd = new jsonData();
        jd.setActivity(itemListActivity.this);
        jd.setAdapter(mAdapter);
        //mAdapter.notifyDataSetChanged();
        jsonData.items = items;
        jd.set_section_name(section_name);

        String id = "4";
        Integer iid = 4;
        try{
            id = getIntent().getStringExtra("com.vermilionsynergy.magadidriver.item_id").toString();
            iid = Integer.parseInt(id);
        } catch (Exception e){}

        final Spinner spinner = (Spinner)findViewById(R.id.filterSort);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.filters, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.filters);
                String params = "";
                String limit = "10";
                switch (choose[selectedItemPosition]){
                    case "Price A-Z":
                        params = "?limit="+limit+"&order_by=price&sort=asc";
                        break;
                    case "Price Z-A":
                        params = "?limit="+limit+"&order_by=price&sort=desc";
                        break;
                    case "Name A-Z":
                        params = "?limit="+limit+"&order_by=name&sort=asc";
                        break;
                    case "Name Z-A":
                        params = "?limit="+limit+"&order_by=name&sort=desc";
                        break;
                    /*case "Date A-Z":
                        params = "?limit="+limit+"&order_by=updated_at&sort=asc";
                        break;
                    case "Date Z-A":
                        params = "?limit="+limit+"&order_by=updated_at&sort=desc";
                        break;*/
                    default:
                        break;
                }
                jd.getList(params);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "your choice: " + choose[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void menuOpen(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }
    public void menuClick(View view){
        menuOpen();
    }
    public void cartClick(View view){
        //Intent intent = new Intent(this, CartActivity.class);
        //startActivity(intent);
    }
    public void searchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
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
            convertView = layoutInflater.inflate(R.layout.fragment_item_list, null);
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
            String ImgUrl = item.getImg();
            Button button = (Button)convertView.findViewById(R.id.btnAdd);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), item.getName() + "added to cart" ,Toast.LENGTH_SHORT).show();
                }
            });
            ImageView iv = (ImageView)convertView.findViewById(R.id.imageInList);
            iv.setOnClickListener(ocl);
            if (ImgUrl != null) {
                new BitmapWorkerTask(iv).execute(ImgUrl);
            }

            return convertView;
        }
    }
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private String imageUrl;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage
            // collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            return LoadImage(imageUrl);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        private Bitmap LoadImage(String URL) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                in = OpenHttpConnection(URL);
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e1) {
            }
            return bitmap;
        }

        private InputStream OpenHttpConnection(String strURL)
                throws IOException {
            InputStream inputStream = null;
            URL url = new URL(strURL);
            URLConnection conn = url.openConnection();

            try {
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = httpConn.getInputStream();
                }
            } catch (Exception ex) {
            }
            return inputStream;
        }
    }
}
