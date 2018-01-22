package com.example.demouser.motrade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by demouser on 1/16/18.
 */

public class BrowsingActivity extends AppCompatActivity {

    protected static Catalog catalog = new Catalog();
    protected static ListView listView1;
    protected static ListViewAdapter adapter;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create catalog and add hardcoded entries
        //Catalog catalog = new Catalog();
        Listing newListing =  new Listing("name1",  "$1837","description 1", "sellerName 1", Listing.Category.SERVICE);
        newListing.addPicture(R.drawable.puppy, true);
        Listing newListing2 =  new Listing("name2",  "$1837","description 2", "sellerName 2", Listing.Category.SERVICE);
        newListing2.addPicture(R.drawable.puppy, true);
        Listing newListing3 =  new Listing("name3",  "$1837","description 3", "sellerName 3", Listing.Category.SERVICE);
        newListing3.addPicture(R.drawable.puppy, true);
        Listing newListing4 =  new Listing("name4",  "$1837","description 4", "sellerName 4", Listing.Category.SERVICE);
        newListing4.addPicture(R.drawable.puppy, true);
        catalog.addListing(newListing);
        catalog.addListing(newListing2);
        catalog.addListing(newListing3);
        catalog.addListing(newListing4);

        //make listview
        listView1 = (ListView) findViewById(R.id.listView1);

        //get list from catalog
        ArrayList<Listing> items = catalog.getMasterList();

        //make adapter, passing in list from catlog
        adapter = new ListViewAdapter(this, R.layout.list_item, items);

        //set listView's adapter
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Listing itemValue = (Listing) listView1.getItemAtPosition(position);
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

                // update and display the catalog
                Listing newListing;
                if (newListingCategory.toLowerCase().equals("good")) {
                    newListing = new Listing(newListingName, newListingPrice, newListingDesc, "", Listing.Category.GOOD);
                }
                else {
                    newListing = new Listing(newListingName, newListingPrice, newListingDesc, "", Listing.Category.SERVICE);
                }

                newListing.addPicture(R.drawable.puppy, true);
                catalog.addListing(newListing);
                adapter.notifyDataSetChanged();

            }
        }
    }

}