package edu.odu.hackathon.plato.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.Format;
import edu.odu.hackathon.plato.model.Chat;

/**
 * Created by kahmed on 2/5/16.
 */
public class CustomChatAdapter extends BaseAdapter {

    String TAG = "CustomChatAdapter";
    public Context mContext;
    public ArrayList<Chat> mValues;
    public int selfId;

    public CustomChatAdapter(Context context, ArrayList<Chat> values, int selfId) {
        Log.v(TAG,"Started");
        this.mContext = context;
        this.mValues = values;
        this.selfId = selfId;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Chat getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        Chat item = new Chat(mValues.get(position));
        Log.d(TAG, "Chat Item: " + item.toString());

        if(item.getUserId() == selfId) {
            rowView = layoutInflater.inflate(R.layout.chat_self, null);
        }
        else {
            rowView = layoutInflater.inflate(R.layout.chat_other, null);
        }
        TextView tvUserName = (TextView) rowView.findViewById(R.id.tvChatUserName);
        tvUserName.setText(item.getDisplayName());
        if(item.getUserId() == selfId) {
            tvUserName.setText("You");
        }
        TextView tvChatText = (TextView) rowView.findViewById(R.id.tvChatText);
        tvChatText.setText(item.getMessage());
        TextView tvChatTime = (TextView) rowView.findViewById(R.id.tvChatTime);
        tvChatTime.setText(Format.getFuzzyTime(item.getEpochTime()).toString());

        return rowView;
    }
}
