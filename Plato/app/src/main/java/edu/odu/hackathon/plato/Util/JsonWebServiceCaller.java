package edu.odu.hackathon.plato.Util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Handson_2 on 2/4/2016.
 */
public class JsonWebServiceCaller {

    String TAG = "JsonWebServiceCaller";
    public static String call(String resourcepath,String input)
    {
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        StringBuffer buffer=new StringBuffer("");
        try {
            URL url = new URL(resourcepath);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = input;

            // Send post request
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            //connection.connect();
            System.out.println(responseCode);

            InputStream stream=connection.getInputStream();
            reader=new BufferedReader(new InputStreamReader(stream));


            String line="";
            while ((line= reader.readLine())!=null)
            {
                buffer.append(line);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void postData(String val)
    {
    }
}
