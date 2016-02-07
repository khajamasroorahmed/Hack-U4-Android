package edu.odu.hackathon.plato.book;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Random;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.JsonWebServiceCaller;
import edu.odu.hackathon.plato.Util.OnSwipeTouchListener;
import edu.odu.hackathon.plato.model.BookRequest;
import edu.odu.hackathon.plato.search.SearchActivity;
import edu.odu.hackathon.plato.university.UniversityActivity;

public class BookActivity extends Activity {

    String TAG = "BookActivity";
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
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

        ImageView university=(ImageView)findViewById(R.id.barCode);
        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               scanBar(v);
            }
        });

        ImageView search=(ImageView)findViewById(R.id.qrCode);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR(v);
            }
        });

        SearchView searchView=(SearchView)findViewById(R.id.searchBook);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new MatchingTask().execute(query);
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
            String urlParameters = "{\"userId\": 1, \"isbn\": \""+params[0]+"\"}";
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
                Button bookConversastion=(Button)findViewById(R.id.bookConversastion);

                if(request.getMatches().size()==0)
                {
                    title.setText("");
                    univName.setText("");
                    majorName.setText("");
                    bookinterestsTitle.setVisibility(View.INVISIBLE);
                    bookConversastion.setVisibility(View.INVISIBLE);
                    showInterests(getApplicationContext(), "".split(", "));
                    Toast.makeText(getApplicationContext(),"No Matches for this book",Toast.LENGTH_LONG).show();
                    count=0;
                }
                else
                {
                    title.setText(request.getMatches().get(0).getDisplayName());
                    univName.setText(request.getMatches().get(0).getUniversity());
                    majorName.setText(request.getMatches().get(0).getMajor());
                    bookinterestsTitle.setVisibility(View.VISIBLE);
                    bookConversastion.setVisibility(View.VISIBLE);
                    showInterests(getApplicationContext(), request.getMatches().get(0).getBooks().split(", "));
                    count++;
                }


                System.out.println(request.getMatches().size());

            }
            catch (NullPointerException nullpointer)
            {
                TextView title=(TextView)findViewById(R.id.profileName);
                TextView univName=(TextView)findViewById(R.id.bookUnivName);
                TextView majorName=(TextView)findViewById(R.id.bookmMajorName);
                TextView bookinterestsTitle=(TextView)findViewById(R.id.bookInterestsTitle);
                Button bookConversastion=(Button)findViewById(R.id.bookConversastion);
                bookConversastion.setVisibility(View.INVISIBLE);

                title.setText("");
                univName.setText("");
                majorName.setText("");
                showInterests(getApplicationContext(),"".split(", "));
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

    //product barcode mode
    public void scanBar(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            //showDialog(getApplicationContext(), "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //product qr code mode
    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(BookActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //alert dialog for downloadDialog
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                SearchView view=(SearchView)findViewById(R.id.searchBook);
                view.setQuery(contents,true);
                //Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                //toast.show();
            }
        }
    }
}
