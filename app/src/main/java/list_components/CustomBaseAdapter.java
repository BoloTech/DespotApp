package list_components;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bojan.despotapp.R;

import java.util.ArrayList;

import database.DatabaseHelper;

/**
 * Created by Bojan on 5/4/2016.
 */
public class CustomBaseAdapter extends BaseAdapter {

    ArrayList<SingleRow> mSingleRowArrayList;
    Context context;

    public CustomBaseAdapter(Context context) {
        mSingleRowArrayList = new ArrayList<SingleRow>();
        this.context = context;

        insertDataInList();
    }

    private void insertDataInList() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Cursor cursor = databaseHelper.readData();

        if (cursor.getCount() != 0)
        {
           if(cursor.moveToFirst())
               mSingleRowArrayList.add(new SingleRow(cursor.getString(0), cursor.getString(1), cursor.getString(2)));

            while(cursor.moveToNext())
                mSingleRowArrayList.add(new SingleRow(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }

    @Override
    public int getCount() {
        return mSingleRowArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSingleRowArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_row_renderer, parent, false);

        TextView regNumber = (TextView) v.findViewById(R.id.id_regNumberTextView);
        TextView price = (TextView) v.findViewById(R.id.id_priceTextView);
        TextView date = (TextView) v.findViewById(R.id.id_dateTextView);

        SingleRow singleRow = mSingleRowArrayList.get(position);

        regNumber.setText(singleRow.regNumbers);
        price.setText(singleRow.prices);
        date.setText(singleRow.dates);

        return v;
    }
}

class SingleRow
{
    String regNumbers;
    String prices;
    String dates;

    public SingleRow(String regNumbers, String prices, String dates) {
        this.regNumbers = regNumbers;
        this.prices = prices;
        this.dates = dates;
    }
}
