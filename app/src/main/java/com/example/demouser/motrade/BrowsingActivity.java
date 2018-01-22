package com.example.demouser.motrade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by demouser on 1/16/18.
 */

public class BrowsingActivity extends AppCompatActivity {

    protected static Catalog catalog = new Catalog();
    protected static ListView listView3, listView2, listView1;
    protected static LinearLayout tab1, tab2, tab3;
    protected TabHost host;
    protected static ListViewAdapter adapter1, adapter2, adapter3;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String address = "/res/drawable/puppy.jpeg";
        Listing newListing =  new Listing("name1",  "$1837","description 1", "sellerName 1", Listing.Category.SERVICE, "");
        catalog.addListing(newListing);
        /*
        // create catalog and add hardcoded entries
        //Catalog catalog = new Catalog();
        Listing newListing =  new Listing("name1",  "$1837","description 1", "sellerName 1", Listing.Category.GOOD, puppyString);
        Listing newListing2 =  new Listing("name2",  "$1837","description 2", "sellerName 2", Listing.Category.SERVICE, puppyString);
        Listing newListing3 =  new Listing("name3",  "$1837","description 3", "sellerName 3", Listing.Category.SERVICE, puppyString);
        Listing newListing4 =  new Listing("name4",  "$1837","description 4", "sellerName 4", Listing.Category.GOOD, puppyString);
        catalog.addListing(newListing);
        catalog.addListing(newListing2);
        catalog.addListing(newListing3);
        catalog.addListing(newListing4);

        // make tab host and three tabs
        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        TabHost.TabSpec spec = host.newTabSpec("All items");
        spec.setContent(R.id.tab1);
        spec.setIndicator("All items");
        host.addTab(spec);
        spec = host.newTabSpec("Goods");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Goods");
        host.addTab(spec);
        spec = host.newTabSpec("Service");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Service");
        host.addTab(spec);

        //make listview
        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);
        listView3 = (ListView) findViewById(R.id.listView3);

        //get list from catalog
        ArrayList<Listing> items = catalog.getMasterList();
        ArrayList<Listing> goodItems = catalog.getCategory(Listing.Category.GOOD);
        ArrayList<Listing> serviceItems = catalog.getCategory(Listing.Category.SERVICE);

        //make adapter, passing in list from catlog
        adapter1 = new ListViewAdapter(this, R.layout.list_item, items);
        adapter2 = new ListViewAdapter(this, R.layout.list_item, goodItems);
        adapter3 = new ListViewAdapter(this, R.layout.list_item, serviceItems);

        //set listView's adapter
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Listing itemValue = (Listing) listView1.getItemAtPosition(position);
                createIntent(itemValue);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Listing itemValue = (Listing) listView2.getItemAtPosition(position);
                createIntent(itemValue);
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Listing itemValue = (Listing) listView3.getItemAtPosition(position);
                createIntent(itemValue);
            }
        });

        FloatingActionButton addItemButton = (FloatingActionButton) findViewById(R.id.newItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAddItemIntent();
            }
        });

    }
    // Create the intent for 2nd activity
    public void createIntent(Listing listing) {
        Intent intent = new Intent(this, ItemViewActivity.class);
        intent.putExtra("name", listing.getName());
        intent.putExtra("price", listing.getPrice());
        intent.putExtra("desc", listing.getDescription());
        intent.putExtra("picture", listing.getMainPicture());
        startActivity(intent);
    }

    public void createAddItemIntent() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "New product posted!", Toast.LENGTH_LONG).show();

                // get data from return intent
                String newListingName = data.getStringExtra("name");
                String newListingPrice = data.getStringExtra("price");
                String newListingDesc = data.getStringExtra("desc");
                String newListingCategory = data.getStringExtra("category");
                String newListingImageAdress = data.getStringExtra("image");

                // update and display the catalog
                Listing newListing;
                if (newListingCategory.toLowerCase().equals("good")) {
                    newListing = new Listing(newListingName, newListingPrice, newListingDesc, "", Listing.Category.GOOD, newListingImageAdress);
                }
                else {
                    newListing = new Listing(newListingName, newListingPrice, newListingDesc, "", Listing.Category.SERVICE, newListingImageAdress);
                }

                catalog.addListing(newListing);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
                adapter3.notifyDataSetChanged();
            }
        }
    }

}