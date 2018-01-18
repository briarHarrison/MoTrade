package com.example.demouser.motrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
/**
 * Created by demouser on 1/16/18.
 */

public class BrowsingActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView1 = (ListView) findViewById(R.id.listView1);

        Listing newListing =  new Listing("name1", "description 1", "sellerName 1", Listing.Category.SERVICE);
        newListing.addPicture(R.drawable.cheese, true);
        //makes an array of hardcoded listings.
        //Eventually, we will delete this
        Listing[] items = {
                newListing
                //new Listing("name1", "description 1", "sellerName 1", Listing.Category.SERVICE),
                //new Listing("name2", "description 2", "sellerName 2", Listing.Category.SERVICE),
                //new Listing("name3", "description 3", "sellerName 3", Listing.Category.SERVICE),
                //new Listing("name4", "description 4", "sellerName 4", Listing.Category.SERVICE),
                //new Listing("name5", "description 5", "sellerName 5", Listing.Category.SERVICE)
        };

        //An adapter takes the array (items) and uses it to format each line of the ListView
        //simple_list_item_1 is a premade andoid xml document to describe the default view of a
        //string in a ListView
        /*
        ArrayAdapter<Listing> adapter = new ArrayAdapter<Listing>(this,
                android.R.layout.simple_list_item_1, items); */

        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_item, items);

        //set listView's adapter
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int itemPosition  = position;
                String itemValue = (String) listView1.getItemAtPosition(position);
            }
        });

    }
    public void createIntent() {
        Intent intent = new Intent(this, ListingDetailView.class);
        startActivity(intent);
    }
}