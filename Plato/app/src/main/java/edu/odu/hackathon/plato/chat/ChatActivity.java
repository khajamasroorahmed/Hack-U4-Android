package edu.odu.hackathon.plato.chat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.model.Chat;

/**
 * Created by kahmed on 2/4/16.
 */
public class ChatActivity extends Activity {

    String TAG = "ChatActivity";
    CustomChatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"Started");
        setContentView(R.layout.activity_chat);
        Button btSend = (Button) findViewById(R.id.btChatSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send the message to the server and also update on screen
            }
        });
        ArrayList<Chat> values = new ArrayList<Chat>(Arrays.asList(new Chat(1,1454653570970L,"BOoyah","Masroor"),
                new Chat(2,1454653571500L,"waddup","Masroor"),new Chat(1,1454653572000L,"hello","Masroor")));

        mAdapter = new CustomChatAdapter(this, values, 1);
        ListView lvChat = (ListView) findViewById(R.id.lvActivityChat);
        lvChat.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
