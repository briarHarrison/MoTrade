package com.example.demouser.motrade;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        //get button
        Button postButton = (Button) findViewById(R.id.addButton);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            picture = findViewById(R.id.itemIconUploader);
            picture.setEnabled(false);
        }

        //set button listener
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.itemNameInput);
                EditText price = (EditText) findViewById(R.id.itemPriceInput);
                EditText description = (EditText) findViewById(R.id.itemDescriptionInput);
                EditText category = (EditText) findViewById(R.id.itemCategoryInput);

                // put the String to pass back into an Intent and close this activity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("name", name.getText().toString());
                returnIntent.putExtra("price", price.getText().toString());
                returnIntent.putExtra("desc", description.getText().toString());
                returnIntent.putExtra("category", category.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            picture.setImageBitmap(imageBitmap);
        }
    }

}
