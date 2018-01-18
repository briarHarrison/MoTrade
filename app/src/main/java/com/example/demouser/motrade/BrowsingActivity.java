package com.example.demouser.motrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by demouser on 1/16/18.
 */

public class BrowsingActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create catalog and add hardcoded entries
        Catalog catalog = new Catalog();
        Listing newListing =  new Listing("name1", "description 1", "sellerName 1", Listing.Category.SERVICE);
        newListing.addPicture(R.drawable.cheese, true);
        Listing newListing2 =  new Listing("name2", "description 2", "sellerName 2", Listing.Category.SERVICE);
        newListing2.addPicture(R.drawable.cheese, true);
        Listing newListing3 =  new Listing("name3", "description 3", "sellerName 3", Listing.Category.SERVICE);
        newListing3.addPicture(R.drawable.cheese, true);
        Listing newListing4 =  new Listing("name4", "description 4", "sellerName 4", Listing.Category.SERVICE);
        newListing4.addPicture(R.drawable.cheese, true);
        catalog.addListing(newListing);
        catalog.addListing(newListing2);
        catalog.addListing(newListing3);
        catalog.addListing(newListing4);

        //make listview
        final ListView listView1 = (ListView) findViewById(R.id.listView1);

        //get list from catalog
        ArrayList<Listing> items = catalog.getMasterList();

        //make adapter, passing in list from catlog
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_item, items);

        //set listView's adapter
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Listing itemValue = (Listing) listView1.getItemAtPosition(position);
                createIntent(itemValue);
            }
        });

    }
    public void createIntent(Listing listing) {
        Intent intent = new Intent(this, ItemViewActivity.class);
        intent.putExtra("name", listing.name);
        startActivity(intent);
    }
}