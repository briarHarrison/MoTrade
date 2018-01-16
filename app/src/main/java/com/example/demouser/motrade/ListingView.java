package com.example.demouser.motrade;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by demouser on 1/16/18.
 */

public class ListingView extends View {

    private Listing listing;

    public ListingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Listing getListing() {
        return listing;
    }
}
