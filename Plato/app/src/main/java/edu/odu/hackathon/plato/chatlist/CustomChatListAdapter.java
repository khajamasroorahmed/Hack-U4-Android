package edu.odu.hackathon.plato.chatlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.odu.hackathon.plato.Util.ImageText;
import edu.odu.hackathon.plato.R;

/**
 * Created by kahmed on 2/4/16.
 */
public class CustomChatListAdapter extends BaseAdapter {

    String TAG = "CustomChatListAdapter";
    public Context mContext;
    public ArrayList<String> mValues;
    ImageView mIvIcon;
    ImageText mImageText;

    public CustomChatListAdapter(Context context, ArrayList<String> values) {
        Log.v(TAG, "Started");
        this.mContext = context;
        this.mValues = values;
        this.mImageText = new ImageText(context);
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public String getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.chat_list, null);
        TextView tvPseudonym = (TextView) rowView.findViewById(R.id.tvChatName);
        tvPseudonym.setText(getItem(position));
        mIvIcon = (ImageView) rowView.findViewById(R.id.rivChat);
        setRandomImageIcon();
        return rowView;
    }

    public void setRandomImageIcon() {
        mIvIcon.setImageResource(R.drawable.university);
    }

}
