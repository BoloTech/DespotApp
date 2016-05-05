package com.example.bojan.despotapp;

import android.support.v7.app.ActionBar;

/**
 * Created by Bojan on 5/5/2016.
 */
public class Utils {

    public static void setBackButtonOnActionBar(ActionBar actionBar)
    {
        actionBar.setTitle(Constants.EMPTY_STRING);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
