package edu.odu.hackathon.plato.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.JsonWebServiceCaller;
import edu.odu.hackathon.plato.Util.StaticData;
import edu.odu.hackathon.plato.home.HomeActivity;
import edu.odu.hackathon.plato.interests.CustomInterestAdapter;
import edu.odu.hackathon.plato.model.Chat;
import edu.odu.hackathon.plato.model.Match;
import edu.odu.hackathon.plato.model.MatchingRequest;
import edu.odu.hackathon.plato.university.UniversityActivity;

/**
 * Created by kahmed on 2/4/16.
 */
public class SearchActivity extends AppCompatActivity {
    public int count=0;

    Button mBtnSubmit;
    GridView mGridView;
    EditText mEditTextSearch;
    ArrayList<String> values;
    CustomInterestAdapter mAdapter;
    private String TAG = "InterestActivity";
    String search;
    public MatchingRequest request;
    RelativeLayout relativeLayout;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Started");
        setContentView(R.layout.interests);
        mGridView = (GridView) findViewById(R.id.lvInterests);
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
        mGridView.setAdapter(mAdapter);
        mGridView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Item Selected: " + mGridView.getCheckedItemCount());
                TextView tv = (TextView) view.findViewById(R.id.tvInterest);
                search = tv.getText().toString();
            }
        });
        mBtnSubmit = (Button) findViewById(R.id.button);
        mBtnSubmit.setText("Search");
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UniversityActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
