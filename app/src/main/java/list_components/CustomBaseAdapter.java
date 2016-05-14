package list_components;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.bojan.despotapp.R;

import java.util.ArrayList;

import database.DatabaseHelper;

/**
 * Created by Bojan on 5/4/2016.
 */
public class CustomBaseAdapter extends BaseAdapter implements Filterable {

    ArrayList<SingleRow> mSingleRowArrayList;
    ArrayList<SingleRow> mHelpSingleRowArrayList;

    Context context;
    ValueFilter valueFilter;

    public CustomBaseAdapter(Context context) {
        mSingleRowArrayList = new ArrayList<SingleRow>();

        this.context = context;

        insertDataInList();
    }

    private void insertDataInList() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        Cursor cursor = databaseHelper.readData();

        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst())
                mSingleRowArrayList.add(new SingleRow(cursor.getString(0), cursor.getString(1), cursor.getString(2)));

            while (cursor.moveToNext())
                mSingleRowArrayList.add(new SingleRow(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        mHelpSingleRowArrayList = mSingleRowArrayList;
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

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<SingleRow> filterList = new ArrayList<SingleRow>();
                for (int i = 0; i < mHelpSingleRowArrayList.size(); i++) {
                    if ((mHelpSingleRowArrayList.get(i).regNumbers.toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        SingleRow sr = new SingleRow(mHelpSingleRowArrayList.get(i)
                                .regNumbers, mHelpSingleRowArrayList.get(i)
                                .prices, mHelpSingleRowArrayList.get(i)
                                .dates);

                        filterList.add(sr);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mHelpSingleRowArrayList.size();
                results.values = mHelpSingleRowArrayList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mSingleRowArrayList = (ArrayList<SingleRow>) results.values;
            notifyDataSetChanged();
        }
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
