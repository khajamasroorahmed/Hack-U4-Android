package edu.odu.hackathon.plato.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Handson_2 on 2/4/2016.
 */
public class BookRequest {

    List<Book> matches=new ArrayList<Book>();

    public BookRequest()
    {

    }

    public List<Book> getMatches() {
        return matches;
    }

    public void setMatches(List<Book> matches) {
        this.matches = matches;
    }
}
