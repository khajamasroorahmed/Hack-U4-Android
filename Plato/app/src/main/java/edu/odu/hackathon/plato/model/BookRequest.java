package edu.odu.hackathon.plato.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Handson_2 on 2/4/2016.
 */
public class BookRequest {

    String TAG = "";
    List<Book> matches = new ArrayList<Book>();

    public BookRequest() {
        Log.v(TAG, "Started");
    }

    public List<Book> getMatches() {
        return matches;
    }

    public void setMatches(List<Book> matches) {
        this.matches = matches;
    }
}
