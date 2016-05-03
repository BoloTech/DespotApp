package com.example.bojan.despotapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Menu mMenu;

    private Button mNewWashBtn;
    private Button mWashListBtn;
    private Button mSalaryListBtn;

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        if (!first)
        {
            setContentView(R.layout.activity_main);
            setUpListeners();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.id_casualFont:
                removeSelection();
                item.setChecked(true);

                setTheme(R.style.AppThemeCasualFont);
                Toast.makeText(MainActivity.this, "Casual font set", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.id_cursiveFont:
                removeSelection();
                item.setChecked(true);

                setTheme(R.style.AppThemeCursiveFont);
                Toast.makeText(MainActivity.this, "Cursive font set", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.id_monospaceFont:
                removeSelection();
                item.setChecked(true);

                setTheme(R.style.AppThemeMonospaceFont);
                Toast.makeText(MainActivity.this, "Monospace font set", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Remove selection item in menu list */
    protected void removeSelection()
    {
        for (int i = 0; i < mMenu.getItem(0).getSubMenu().size(); i++)
            mMenu.getItem(0).getSubMenu().getItem(i).setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        mMenu = menu;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.podesavanja);

        setUpListeners();
    }

    protected void setUpListeners()
    {
        //       New Wash
        mNewWashBtn = (Button) findViewById(R.id.id_newWashBtn);
        mNewWashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherActivity(NewWashActivity.class);
            }
        });

//       Wash List
        mWashListBtn = (Button) findViewById(R.id.id_washListBtn);
        mWashListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//       Salary list
        mSalaryListBtn = (Button) findViewById(R.id.id_salaryListBtn);
        mSalaryListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void startAnotherActivity(Class activity)
    {
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}