package com.example.bojan.despotapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Spinner;

public class NewWashActivity extends AppCompatActivity {

    private Spinner mChooseWash;
    private Spinner mChooseTypeVehicle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wash_layout);

        getSupportActionBar().setTitle(Constants.EMPTY_STRING);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChooseWash = (Spinner) findViewById(R.id.id_chooseWash);
        mChooseTypeVehicle = (Spinner) findViewById(R.id.id_typeVehicle);
    }
}
