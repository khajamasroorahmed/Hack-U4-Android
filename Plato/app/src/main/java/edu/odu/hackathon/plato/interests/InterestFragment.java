package edu.odu.hackathon.plato.interests;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.odu.hackathon.plato.R;

/**
 * Created by kahmed on 2/4/16.
 */
public class InterestFragment extends Fragment {

    ListView mListView;
    private String TAG = "InterestFragment";
    String[] values;
    CustomInterestAdapter mAdapter;
    OnDataPass mDataPass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = (ListView) inflater.inflate(R.layout.interests_list_view, null);
        values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};
//        final ListView listview = (ListView) mListView.findViewById(R.id.lvInterests);
//        mAdapter = new CustomInterestAdapter(InterestFragment.this.getActivity(), values);
//        mListView.setAdapter(mAdapter);
//        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        mListView.setItemsCanFocus(false);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "Item Selected: " + mListView.getCheckedItemCount());
//                passData(mListView.getCheckedItemCount());
//            }
//        });

        return mListView;
    }

    public interface OnDataPass {
        public void onDataPass(int count);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDataPass = (OnDataPass) context;
    }

    public void passData(int data) {
        mDataPass.onDataPass(data);
    }

//    private void displayCheckedItemsToast() {
//        SparseBooleanArray sparseBooleanArray = mListView.getCheckedItemPositions();
//        ArrayList<String> selectedItems = new ArrayList<String>();
//        String temp = "";
//        for(int i=0; i<sparseBooleanArray.size(); i++) {
//            int position = sparseBooleanArray.keyAt(i);
//            if(sparseBooleanArray.valueAt(i)) {
//                selectedItems.add(mAdapter.getItem(position));
//                temp += mAdapter.getItem(position);
//            }
//        }
//        //Toast.makeText(InterestFragment.this.getActivity(), temp,Toast.LENGTH_LONG);
//    }
}