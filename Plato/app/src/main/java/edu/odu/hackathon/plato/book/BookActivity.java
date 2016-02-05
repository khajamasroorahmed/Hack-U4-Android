package edu.odu.hackathon.plato.book;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Random;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.JsonWebServiceCaller;
import edu.odu.hackathon.plato.Util.OnSwipeTouchListener;
import edu.odu.hackathon.plato.model.BookRequest;

public class BookActivity extends AppCompatActivity {

    String TAG = "BookActivity";
    public BookRequest request;
    public int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Started");
        setContentView(R.layout.activity_book);

        View view = (View) findViewById(R.id.bookSwipes);
        final TextView title=(TextView)findViewById(R.id.profileName);
        final TextView univName=(TextView)findViewById(R.id.bookUnivName);
        final TextView majorName=(TextView)findViewById(R.id.bookmMajorName);

        SearchView searchView=(SearchView)findViewById(R.id.searchBook);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new MatchingTask().execute();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        final Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/EMPORO.TTF");

        view.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeLeft() {
                if (count < request.getMatches().size()) {
                    count++;
                    //Toast.makeText(getApplicationContext(), "Swipe Left " + count, Toast.LENGTH_SHORT).show();
                    Random random = new Random();
                    title.setText(request.getMatches().get(count - 1).getDisplayName());
                    univName.setText(request.getMatches().get(count-1).getUniversity());
                    majorName.setText(request.getMatches().get(count-1).getMajor());
                    showInterests(getApplicationContext(), request.getMatches().get(count - 1).getBooks().split(", "));
                }
            }

            @Override
            public void onSwipeRight() {
                if (count > 1) {
                    count--;
                    Random random = new Random();
                    //Toast.makeText(getApplicationContext(), "Swipe Right " + count, Toast.LENGTH_SHORT).show();
                    title.setText(request.getMatches().get(count - 1).getDisplayName());
                    univName.setText(request.getMatches().get(count-1).getUniversity());
                    majorName.setText(request.getMatches().get(count-1).getMajor());
                    showInterests(getApplicationContext(), request.getMatches().get(count - 1).getBooks().split(", "));
                }
            }

        });
    }

    class MatchingTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "{\"userId\": 1, \"isbn\": \"1612930425\"}";
            return JsonWebServiceCaller.call("http://platohackathon.herokuapp.com/getBookReadUsers", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                ObjectMapper mapper=new ObjectMapper();
                Random random=new Random();
                request=mapper.readValue(result,BookRequest.class);
                TextView title=(TextView)findViewById(R.id.profileName);
                TextView univName=(TextView)findViewById(R.id.bookUnivName);
                TextView majorName=(TextView)findViewById(R.id.bookmMajorName);
                TextView bookinterestsTitle=(TextView)findViewById(R.id.bookInterestsTitle);
                bookinterestsTitle.setVisibility(View.VISIBLE);
                Button bookConversastion=(Button)findViewById(R.id.bookConversastion);
                bookConversastion.setVisibility(View.VISIBLE);

                title.setText(request.getMatches().get(0).getDisplayName());
                univName.setText(request.getMatches().get(0).getUniversity());
                majorName.setText(request.getMatches().get(0).getMajor());
                showInterests(getApplicationContext(),request.getMatches().get(0).getBooks().split(", "));
                count++;
                System.out.println(request.getMatches().size());

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
        GridView listView = (GridView) findViewById(R.id.bookinterests);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);
    }
}
