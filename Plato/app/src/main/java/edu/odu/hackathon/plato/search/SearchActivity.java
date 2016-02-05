package edu.odu.hackathon.plato.search;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.StaticData;
import edu.odu.hackathon.plato.interests.CustomInterestAdapter;

/**
 * Created by kahmed on 2/4/16.
 */
public class SearchActivity extends AppCompatActivity {

    Button mBtnSubmit;
    ListView mListView;
    EditText mEditTextSearch;
    ArrayList<String> values;
    CustomInterestAdapter mAdapter;
    private String TAG = "InterestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Started");
        setContentView(R.layout.interests);
        mListView = (ListView) findViewById(R.id.lvInterests);
        mEditTextSearch = (EditText) findViewById(R.id.etSearch);

        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        values = StaticData.getInterestList();
//        final ListView listview = (ListView) mListView.findViewById(R.id.lvInterests);
        mAdapter = new CustomInterestAdapter(this, values);
        mListView.setAdapter(mAdapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setItemsCanFocus(false);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Item Selected: " + mListView.getCheckedItemCount());
            }
        });
        mBtnSubmit = (Button) findViewById(R.id.button);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
