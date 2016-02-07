package edu.odu.hackathon.plato.registration;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;
import java.util.Random;

import edu.odu.hackathon.plato.R;
import edu.odu.hackathon.plato.Util.JsonWebServiceCaller;
import edu.odu.hackathon.plato.interests.InterestActivity;

public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Button register=(Button)findViewById(R.id.register);
        final TextView name=(TextView)findViewById(R.id.studentName);
        final TextView email=(TextView)findViewById(R.id.studentEmail);
        final TextView major=(TextView)findViewById(R.id.studentMajor);
        final TextView university=(TextView)findViewById(R.id.studentUniversity);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname=name.getText().toString();
                String semail=email.getText().toString();
                String smajor=major.getText().toString();
                String suniv=university.getText().toString();
                new RegistrationTask().execute(sname,semail,smajor,suniv);
            }
        });
    }

    class RegistrationTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String urlParameters = "{\"email\": \""+params[1]+"\", \"password\": \"1234\",\"university\":\""+params[3]+"\",\"major\":\""+params[2]+"\",\"displayName\":\""+params[0]+"\"}";
            System.out.println("url:"+urlParameters);
            return JsonWebServiceCaller.call("http://platohackathon.herokuapp.com/registration", urlParameters);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Intent intent=new Intent(getApplicationContext(), InterestActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Registered Perfect!!",Toast.LENGTH_LONG).show();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }

        }

    }


}
