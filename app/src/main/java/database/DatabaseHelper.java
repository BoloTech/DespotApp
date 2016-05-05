package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bojan.despotapp.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bojan on 5/2/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String CREATE_TABLE_SQL="create table if not exists " + Database.TABLE_NAME.getName() + " (" + Database.REG_NUMBER.getName() + Constants.COMMA +
                                    Database.PRICE.getName() + Constants.COMMA + Database.DATE.getName() + ")";

    public DatabaseHelper(Context context) {
        super(context, Database.DATABASE_NAME.getName(), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nothing for now...
    }

    public long insertData(String regNumber, String price)
    {
       SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Database.REG_NUMBER.getName(), regNumber);
        cv.put(Database.PRICE.getName(), price);
        cv.put(Database.DATE.getName(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        
        return db.insert(Database.TABLE_NAME.getName(), null, cv);
    }

    public Cursor readData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{Database.REG_NUMBER.getName(), Database.PRICE.getName(), Database.DATE.getName()};
        
        return db.query(Database.TABLE_NAME.getName(), columns, null, null, null, null, null);
    }
}
