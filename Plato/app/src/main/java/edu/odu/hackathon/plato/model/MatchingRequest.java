package edu.odu.hackathon.plato.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Handson_2 on 2/4/2016.
 */
public class MatchingRequest {

    List<Match> matches=new ArrayList<Match>();

    public MatchingRequest()
    {

    }

    public MatchingRequest(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
