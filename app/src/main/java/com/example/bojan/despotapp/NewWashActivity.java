package com.example.bojan.despotapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import database.DatabaseHelper;

public class NewWashActivity extends AppCompatActivity {

    private Spinner mChooseWash;
    private Spinner mChooseTypeVehicle;

    private Button mSaveBtn;

    private EditText mPriceEditText;
    private EditText mRegisterNumber;

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

        mPriceEditText = (EditText) findViewById(R.id.id_price);
        mRegisterNumber = (EditText) findViewById(R.id.id_registerNumber);

        mSaveBtn = (Button) findViewById(R.id.id_saveBtn);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewWashActivity.this);
                alertDialog.setTitle("Da li želite da sačuvate ?");
                alertDialog.setMessage("Registarska oznaka: " + mRegisterNumber.getText().toString() + "\n" +
                                        "Cena: " + mPriceEditText.getText().toString() + "din.");
                alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                        if (databaseHelper.insertData(mRegisterNumber.getText().toString(), mPriceEditText.getText().toString()) != -1)
                        {
                            Toast.makeText(NewWashActivity.this, R.string.uspešno_sačuvano, Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(NewWashActivity.this, R.string.nije_uspešno_sačuvano, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.setNegativeButton(R.string.otkazi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing happen...
                    }
                });
                alertDialog.show();
            }
        });
    }
}
