package com.example.demouser.motrade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        //get button
        Button postButton = (Button) findViewById(R.id.addButton);

        //set button listener
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.itemNameInput);
                EditText price = (EditText) findViewById(R.id.itemPriceInput);
                EditText description = (EditText) findViewById(R.id.itemDescriptionInput);
                CheckBox good = (CheckBox) findViewById(R.id.good);
                CheckBox service = (CheckBox) findViewById(R.id.service);
                String category;
                if (good.isChecked()) {
                    category = "GOOD";
                }
                else {
                    category = "SERVICE";
                }

                // put the String to pass back into an Intent and close this activity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("name", name.getText().toString());
                returnIntent.putExtra("price", price.getText().toString());
                returnIntent.putExtra("desc", description.getText().toString());
                returnIntent.putExtra("category", category);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}
