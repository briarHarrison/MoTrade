package com.example.demouser.motrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ArrayAdapter;
/**
 * Created by demouser on 1/16/18.
 */

public class BrowsingActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView) findViewById(R.id.listView1);

        //makes an array of hardcoded listings.
        //Eventually, we will delete this
        Listing[] items = {
                new Listing("name1", "description 1", "sellerName 1", Listing.Category.SERVICE),
                new Listing("name2", "description 2", "sellerName 2", Listing.Category.SERVICE),
                new Listing("name3", "description 3", "sellerName 3", Listing.Category.SERVICE),
                new Listing("name4", "description 4", "sellerName 4", Listing.Category.SERVICE),
                new Listing("name5", "description 5", "sellerName 5", Listing.Category.SERVICE)
        };

        //An adapter takes the array (items) and uses it to format each line of the ListView
        //simple_list_item_1 is a premade andoid xml document to describe the default view of a
        //string in a ListView
        ArrayAdapter<Listing> adapter = new ArrayAdapter<Listing>(this,
                android.R.layout.simple_list_item_1, items);

        //set listView's adapter
        listView1.setAdapter(adapter);
    }
}