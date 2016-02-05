package edu.odu.hackathon.plato.interests;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import edu.odu.hackathon.plato.R;

/**
 * Created by kahmed on 2/4/16.
 */
public class CustomInterestAdapter extends BaseAdapter implements Filterable {

    String TAG = "CustomInterestAdapter";
    public Context mContext;
    public ArrayList<String> mValues;
    public ArrayList<String> mTempValues;
    InterestFilter mInterestFilter;

    public CustomInterestAdapter(Context context, ArrayList<String> values) {
        Log.v(TAG, "Started");
        this.mContext = context;
        this.mValues = values;
        this.mTempValues = values;
    }

    @Override
    public int getCount() {
        return mTempValues.size();
    }

    @Override
    public String getItem(int position) {
        return mTempValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.interest_category, null);
        TextView tvCategory = (TextView) rowView.findViewById(R.id.tvInterest);
        tvCategory.setText(getItem(position));
        return rowView;
    }

    @Override
    public Filter getFilter() {
        if (mInterestFilter == null)
            mInterestFilter = new InterestFilter();

        return mInterestFilter;
    }

    // Filter

    private class InterestFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // Create a FilterResults object
            mTempValues = new ArrayList<>(mValues);
            FilterResults filterResults = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                filterResults.values = mTempValues;
                filterResults.count = mTempValues.size();
            } else {
                ArrayList<String> result = new ArrayList<>();
                for (String value : mTempValues) {
                    if (value.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        result.add(value);
                    }
                }
                filterResults.values = result;
                filterResults.count = result.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mTempValues = (ArrayList<String>) results.values;

            notifyDataSetChanged();
        }
    }
}
