package edu.odu.hackathon.plato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import edu.odu.hackathon.plato.Util.OnSwipeTouchListener;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }
}
