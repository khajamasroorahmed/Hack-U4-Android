package edu.odu.hackathon.plato.chat;

import android.app.Activity;
import android.os.Bundle;

import edu.odu.hackathon.plato.R;

/**
 * Created by kahmed on 2/4/16.
 */
public class ChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
