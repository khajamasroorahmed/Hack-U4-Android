package edu.odu.hackathon.plato.interests;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.StaticData;

/**
 * Created by kahmed on 2/4/16.
 */
public class InterestActivity extends AppCompatActivity {

    private static int MAX_SELECTION = 5;
    Button mBtnSubmit;
    GridView mGridView;
    EditText mEditTextSearch;
    ArrayList<String> values;
    CustomInterestAdapter mAdapter;
    private String TAG = "InterestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Started");
        setContentView(R.layout.interests);
        mGridView = (GridView) findViewById(R.id.lvInterests);
        mEditTextSearch = (EditText) findViewById(R.id.etSearch);
        final Button btnAdd = (Button) findViewById(R.id.btnAdd);

        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());

                if(mAdapter.getCount() == 0) {
                    Log.d(TAG,"Adapter count: " + mAdapter.getCount());
                    btnAdd.setVisibility(View.VISIBLE);
                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Send item to server.
                            StaticData.addInterest(mEditTextSearch.getText().toString());
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        values = StaticData.getInterestList();
//        final ListView listview = (ListView) mListView.findViewById(R.id.lvInterests);
        mAdapter = new CustomInterestAdapter(this, values);
        mGridView.setAdapter(mAdapter);
        mGridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Item Selected: " + mGridView.getCheckedItemCount());
                TextView tvCheck = (TextView) view.findViewById(R.id.tvInterest);
                tvCheck.setBackgroundResource(R.drawable.pressed);
                onDataPass(mGridView.getCheckedItemCount());
            }
        });
        mBtnSubmit = (Button) findViewById(R.id.button);
        mBtnSubmit.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onDataPass(int count) {
        if (count >= MAX_SELECTION) {
            mBtnSubmit.setVisibility(View.VISIBLE);
        } else {
            mBtnSubmit.setVisibility(View.GONE);
        }
    }
}
