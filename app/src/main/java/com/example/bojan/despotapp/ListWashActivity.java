package com.example.bojan.despotapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import list_components.CustomBaseAdapter;

public class ListWashActivity extends AppCompatActivity {

    private ListView mListWash;
    private EditText mSearch;

    private CustomBaseAdapter mCustomBaseAdapter;

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

        mSearch = (EditText) findViewById(R.id.id_search);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mCustomBaseAdapter.getFilter().filter(mSearch.getText());
            }
        });

        mCustomBaseAdapter = new CustomBaseAdapter(this);

        mListWash = (ListView) findViewById(R.id.id_listView);
        mListWash.setTextFilterEnabled(true);
        mListWash.setAdapter(mCustomBaseAdapter);
        }
    }