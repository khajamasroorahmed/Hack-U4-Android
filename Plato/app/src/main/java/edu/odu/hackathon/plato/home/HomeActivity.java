package edu.odu.hackathon.plato.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import edu.odu.hackathon.plato.book.BookActivity;
import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.chatlist.ChatListActivity;
import edu.odu.hackathon.plato.interests.InterestActivity;
import edu.odu.hackathon.plato.university.UniversityActivity;
import edu.odu.hackathon.plato.Util.OnSwipeTouchListener;
import edu.odu.hackathon.plato.chat.ChatActivity;
import edu.odu.hackathon.plato.search.SearchActivity;

public class HomeActivity extends AppCompatActivity {

    String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Started");
        setContentView(R.layout.activity_home);
        View view=(View)findViewById(R.id.homelayout);
        view.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {

            @Override
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), "Swipe Left", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView university=(ImageView)findViewById(R.id.university);
        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UniversityActivity.class);
                startActivity(intent);
            }
        });

        ImageView search=(ImageView)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ImageView book=(ImageView)findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });

        ImageView chat = (ImageView) findViewById(R.id.chaticon);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

        ImageView add = (ImageView) findViewById(R.id.plus);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InterestActivity.class);
                startActivity(intent);
            }
        });

    }
}
