package edu.odu.hackathon.plato.interests;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.odu.hackathon.plato.R;

/**
 * Created by kahmed on 2/4/16.
 */
public class InterestActivity extends AppCompatActivity implements InterestFragment.OnDataPass{

    FragmentTransaction mFragmentTransaction;
    private static int MAX_SELECTION = 5;
    Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interests);
        Fragment interestFragment = new InterestFragment();
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.flInterests, interestFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
        mBtnSubmit = (Button) findViewById(R.id.button);
        mBtnSubmit.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDataPass(int count) {
        if (count >= MAX_SELECTION) {
            mBtnSubmit.setVisibility(View.VISIBLE);
        }
        else {
            mBtnSubmit.setVisibility(View.GONE);
        }
    }
}
