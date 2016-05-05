package com.example.bojan.despotapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import list_components.CustomBaseAdapter;

public class ListWashActivity extends AppCompatActivity {

    private ListView mListWash;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wash);

        Utils.setBackButtonOnActionBar(getSupportActionBar());

        mListWash = (ListView) findViewById(R.id.id_listView);
        mListWash.setAdapter(new CustomBaseAdapter(this));
        }
    }