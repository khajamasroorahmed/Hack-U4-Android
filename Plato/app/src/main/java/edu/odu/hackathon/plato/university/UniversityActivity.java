package edu.odu.hackathon.plato.university;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.JsonWebServiceCaller;
import edu.odu.hackathon.plato.Util.OnSwipeTouchListener;
import edu.odu.hackathon.plato.chat.ChatActivity;
import edu.odu.hackathon.plato.model.Match;
import edu.odu.hackathon.plato.model.MatchingRequest;

public class UniversityActivity extends Activity {

    String TAG = "UniversityActivity";
    public MatchingRequest request;
    public int count=0;
    public String[] interests={"1","2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new MatchingTask().execute();
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Started");
        setContentView(R.layout.activity_university);

        View view = (View) findViewById(R.id.universitySwipes);

        final TextView title=(TextView)findViewById(R.id.personName);
        final TextView univName=(TextView)findViewById(R.id.univName);
        final TextView majorName=(TextView)findViewById(R.id.majorName);
        final ImageView imageView=(ImageView)findViewById(R.id.matchScore);
        final Button univConversation=(Button)findViewById(R.id.univConversation);

        univConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(request.getMatches().get(count-1).getId()));
                bundle.putString("displayName",request.getMatches().get(count-1).getDisplayName());
                Intent intent=new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        final Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/EMPORO.TTF");

        view.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeLeft() {
                if (count < request.getMatches().size()) {
                    count++;
                    //Toast.makeText(getApplicationContext(), "Swipe Left " + count, Toast.LENGTH_SHORT).show();
                    Random random=new Random();
                    title.setText(request.getMatches().get(count-1).getDisplayName());
                    univName.setText(request.getMatches().get(count-1).getUniversity());
                    majorName.setText(request.getMatches().get(count-1).getMajor());
                    circularImageBar(imageView, request.getMatches().get(count - 1).getMatchingPercentage());
                    showInterests(getApplicationContext(), request.getMatches().get(count - 1).getInterests());
                }
            }

            @Override
            public void onSwipeRight() {
                if (count > 1) {
                    count--;
                    Random random=new Random();
                    //Toast.makeText(getApplicationContext(), "Swipe Right " + count, Toast.LENGTH_SHORT).show();
                    title.setText(request.getMatches().get(count-1).getDisplayName());
                    univName.setText(request.getMatches().get(count - 1).getUniversity());
                    majorName.setText(request.getMatches().get(count-1).getMajor());
                    circularImageBar(imageView, request.getMatches().get(count - 1).getMatchingPercentage());
                    showInterests(getApplicationContext(), request.getMatches().get(count - 1).getInterests());
                }
            }
        });

    }

    private void circularImageBar(ImageView iv2, int i) {

        Bitmap b = Bitmap.createBitmap(300, 300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();

        paint.setColor(Color.parseColor("#c4c4c4"));
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(150, 150, 140, paint);
        if(i>75 && i<=100) {
            paint.setColor(Color.parseColor("#00e600"));
        }
        else if(i>50 && i<=75) {
            paint.setColor(Color.parseColor("#ffff99"));
        }
        else if(i>25 && i<=50) {
            paint.setColor(Color.parseColor("#99ff99"));
        }
        else if(i>0 && i<=25) {
            paint.setColor(Color.parseColor("#ff0000"));
        }
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.STROKE);
        oval.set(10, 10, 290, 290);
        canvas.drawArc(oval, 270, ((i * 360) / 100), false, paint);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setTextSize(120);
        canvas.drawText(""+i+"%", 150, 150+(paint.getTextSize()/3), paint);
        iv2.setImageBitmap(b);
    }

    class MatchingTask extends AsyncTask<String, Integer, String>
    {
        private ProgressDialog dialog=new ProgressDialog(UniversityActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("loading matches...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "{\"userid\":\"1\"}";
            return JsonWebServiceCaller.call("http://platohackathon.herokuapp.com/getmatchingprofiles", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                ObjectMapper mapper=new ObjectMapper();
                Random random=new Random();
                request=mapper.readValue(result,MatchingRequest.class);

                Collections.sort(request.getMatches(),new ScoreComp());
                TextView title=(TextView)findViewById(R.id.personName);
                TextView univName=(TextView)findViewById(R.id.univName);
                TextView majorName=(TextView)findViewById(R.id.majorName);
                ImageView imageView=(ImageView)findViewById(R.id.matchScore);
                Button univConversation=(Button)findViewById(R.id.univConversation);

                univConversation.setVisibility(View.VISIBLE);

                title.setText(request.getMatches().get(0).getDisplayName());
                univName.setText(request.getMatches().get(0).getUniversity());
                majorName.setText(request.getMatches().get(0).getMajor());
                circularImageBar(imageView, request.getMatches().get(0).getMatchingPercentage());
                showInterests(getApplicationContext(),request.getMatches().get(0).getInterests());
                count++;
                System.out.println(request.getMatches().size());
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }

        }

    }

    public void showInterests(Context context,String[] items)
    {
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                R.layout.list_interest,items);
        GridView listView = (GridView) findViewById(R.id.interests);
        listView.setHorizontalSpacing(10);
        listView.setVerticalSpacing(10);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);
    }

    class ScoreComp implements Comparator<Match> {

        @Override
        public int compare(Match e1, Match e2) {
            if(e1.getMatchingPercentage() < e2.getMatchingPercentage()){
                return 1;
            } else {
                return -1;
            }
        }
    }
}
