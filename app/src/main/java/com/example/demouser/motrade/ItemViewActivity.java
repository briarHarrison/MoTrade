package com.example.demouser.motrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        TextView textName = (TextView) findViewById(R.id.itemNameView);
        textName.setText(name);
        String desc = intent.getStringExtra("disc");
        TextView textDicc = (TextView) findViewById(R.id.itemDescriptionView);
        textDicc.setText(desc);



        String desc = intent.getStringExtra("desc");
        TextView textDesc = (TextView) findViewById(R.id.itemDescriptionView);
        textDesc.setText(desc);

        String price = intent.getStringExtra("price");
        TextView textPrice = (TextView) findViewById(R.id.itemPriceView);
        textPrice.setText(price);

        Integer pictureFile = intent.getIntExtra("picture", 0);
        ImageView image = (ImageView) findViewById(R.id.itemIcon);
        image.setImageResource(pictureFile);





    }
}
