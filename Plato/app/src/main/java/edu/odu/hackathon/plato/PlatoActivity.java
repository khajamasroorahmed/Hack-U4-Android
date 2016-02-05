package edu.odu.hackathon.plato;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by kahmed on 2/4/16.
 */
public class PlatoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
