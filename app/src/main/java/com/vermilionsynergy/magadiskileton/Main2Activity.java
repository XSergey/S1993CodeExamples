package com.vermilionsynergy.magadiskileton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static Activity __this;
    public static Context __this_context;
    public static ArrayList<DataMain> items = new ArrayList<>();
    public  static Integer[] imgid={
            R.drawable.main1,
            R.drawable.main2,
            R.drawable.main3,
            R.drawable.main1,
            R.drawable.main2,
            R.drawable.main3,
    };
    public static String[] itemname ={
            "1",
            "2",
            "3",
            "1",
            "2",
            "3",
    };
    public static MainListAdapter adapter;
    public static AdapterView adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        WebView wv = (WebView)headerLayout.findViewById(R.id.menuWV);
        wv.setBackgroundColor(Color.WHITE);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new Main2Activity.MyJavaInterface(), "app");
        wv.loadUrl("file:///android_asset/menu.html");
        items.add(new DataMain(1, R.drawable.main1));
        items.add(new DataMain(2, R.drawable.main2));
        items.add(new DataMain(3, R.drawable.main3));
        adapter=new MainListAdapter(this, itemname, imgid);
        __this = this;
        __this_context = getApplicationContext();
    }

    public void BottomLinksClick(View view){
        switch (view.getId()){
            case R.id.btnGoMain:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btnGoNew:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.btnGOSale:
                mViewPager.setCurrentItem(2);
                break;
            default:
                Log.d("BottomLinksClick", "wrong id click");
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
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
    public void cartClick(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
    public void searchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void menuClick(View view){
        menuOpen();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            //return;
        }
    }

    public static class SaveTabJSInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(__this, CartActivity.class);
            //__this.startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void itemOpen(String id) {
            Intent intent = new Intent(__this, itemActivity.class);
            intent.putExtra("com.vermilionsynergy.magadiskileton.item_id", id);
            __this.startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void itemAddToCart(String id) {
            Toast toast = Toast.makeText(__this_context, "item added to cart", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(Main2Activity.this, CartActivity.class);
            //startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void SaleOpen() {
            Log.d("Magadi", "SaleOpen");
            Intent intent = new Intent(Main2Activity.this, SaleActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void NewOpen() {
            Log.d("Magadi", "new Click");
            Intent intent = new Intent(Main2Activity.this, NewActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void mainItemOpen() {
            Intent intent = new Intent(Main2Activity.this, ListActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void faqOpen() {
            Intent intent = new Intent(Main2Activity.this, FaqListActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void deliveryTrackingOpen() {
            Intent intent = new Intent(Main2Activity.this, DeliveryTrackingActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void loginOpen() {
            Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void MenuOpen() {
            menuOpen();
            /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);*/
            /*Intent intent = new Intent(Main2Activity.this, ListActivity.class);
            startActivity(intent);*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                View rootView = inflater.inflate(R.layout.fragment_main_tab1, container, false);
                ListView list=(ListView)rootView.findViewById(R.id.mainList);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        //Intent intent = new Intent(__this, ListActivity.class);
                        Intent intent = new Intent(__this, itemListActivity.class);
                        String Slecteditem= itemname[+position];
                        switch (itemname[+position]){
                            case "1":
                                intent.putExtra("com.vermilionsynergy.magadiskileton.list_id", "1");
                                intent.putExtra("com.vermilionsynergy.magadiskileton.listname", "Frozen");
                                break;
                            case "2":
                                intent.putExtra("com.vermilionsynergy.magadiskileton.list_id", "2");
                                intent.putExtra("com.vermilionsynergy.magadiskileton.listname", "Veg");
                                break;
                            case "3":
                                intent.putExtra("com.vermilionsynergy.magadiskileton.list_id", "3");
                                intent.putExtra("com.vermilionsynergy.magadiskileton.listname", "Bakery");
                                break;
                            default:
                                intent.putExtra("com.vermilionsynergy.magadiskileton.list_id", itemname[+position]);
                                intent.putExtra("com.vermilionsynergy.magadiskileton.listname", "no category");
                                break;
                        }
                        startActivity(intent);
                        Toast.makeText(__this_context, Slecteditem, Toast.LENGTH_SHORT).show();

                    }
                });
                //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            } else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                View rootView = inflater.inflate(R.layout.fragment_main_tab2, container, false);
                WebView mWebView = (WebView)rootView.findViewById(R.id.NewWV);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.addJavascriptInterface(new Main2Activity.SaveTabJSInterface(), "app");
                mWebView.loadUrl("file:///android_asset/new.html");
                return rootView;
            } else {
                View rootView = inflater.inflate(R.layout.fragment_main_tab3, container, false);
                WebView mWebView = (WebView)rootView.findViewById(R.id.SaleWV);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.addJavascriptInterface(new Main2Activity.SaveTabJSInterface(), "app");
                mWebView.loadUrl("file:///android_asset/sale.html");
                //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
            /*View rootView = inflater.inflate(R.layout.fragment_main_tab1, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;*/
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "main";
                case 1:
                    return "new";
                case 2:
                    return "sale";
            }
            return null;
        }
    }
}
