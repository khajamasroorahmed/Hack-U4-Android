package edu.odu.hackathon.plato.chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.JsonWebServiceCaller;
import edu.odu.hackathon.plato.model.BookRequest;
import edu.odu.hackathon.plato.model.Chat;
import edu.odu.hackathon.plato.model.ChatRequest;
import edu.odu.hackathon.plato.model.MatchingRequest;

/**
 * Created by kahmed on 2/4/16.
 */
public class ChatActivity extends AppCompatActivity {

    String TAG = "ChatActivity";
    CustomChatAdapter mAdapter;
    int mId;
    String mDisplayName;
    EditText etMessage;
    String mMessage;
    ArrayList<Chat> values;
    private final int FIVE_SECONDS = 5000;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getInt("id");
        mDisplayName = bundle.getString("displayName");
        getSupportActionBar().setTitle(mDisplayName);
        getSupportActionBar().show();
        Log.v(TAG, "Started");
        setContentView(R.layout.activity_chat);
        handler = new Handler();
        etMessage = (EditText) findViewById(R.id.etChat);
        Button btSend = (Button) findViewById(R.id.btChatSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessage = etMessage.getText().toString();
                etMessage.setText("");
                new SendingTask().execute();
            }
        });
        values = new ArrayList<>();

        mAdapter = new CustomChatAdapter(this, values, 1);
        ListView lvChat = (ListView) findViewById(R.id.lvActivityChat);
        lvChat.setAdapter(mAdapter);

    }

    public void scheduleReceiveChat() {
        handler.postDelayed(new Runnable() {
            public void run() {
                new ReceivingTask().execute();          // this method will contain your almost-finished HTTP calls
                handler.postDelayed(this, FIVE_SECONDS);
            }
        }, FIVE_SECONDS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scheduleReceiveChat();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    class SendingTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String timeInMillis = String.valueOf(System.currentTimeMillis());
            values.add(new Chat(mId, Long.parseLong(timeInMillis), mMessage, mDisplayName));
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userId", mId);
                jsonObject.put("epochTime", timeInMillis);
                jsonObject.put("displayName", mDisplayName);
                jsonObject.put("message", mMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new ReceiverThread().run();

            Log.d(TAG, "Sending Request: " + jsonObject.toString());
            return JsonWebServiceCaller.call("http://platohackathon.herokuapp.com/sendmessage", jsonObject.toString());
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Log.d(TAG, "Result: " + result);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ReceivingTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "Requesting data");
            return JsonWebServiceCaller.call("http://platohackathon.herokuapp.com/recievemessage", "");
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Log.d(TAG, "Result: " + result);
                ObjectMapper mapper = new ObjectMapper();
                Random random = new Random();
                ChatRequest chatList = mapper.readValue(result, ChatRequest.class);

                for (int i = 0; i < chatList.size(); i++) {
                    Chat chat = chatList.getMessageList().get(i);
                    values.add(new Chat(chat.getUserId(), chat.getEpochTime(), chat.getMessage(), chat.getDisplayName()));
                }
                new ReceiverThread().run();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private class ReceiverThread extends Thread {
        @Override
        public void run() {
            ChatActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
