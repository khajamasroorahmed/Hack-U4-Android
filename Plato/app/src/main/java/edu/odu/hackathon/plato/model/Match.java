package edu.odu.hackathon.plato.model;

/**
 * Created by Handson_2 on 2/4/2016.
 */
public class Match {

    int matchingPercentage;
    String id;
    String[] interests;
    String major;
    String university;
    String displayName;

    public Match() {
    }

    public Match(int matchingPercentage, String id,  String[] interests, String displayName,String major,String university) {
        this.matchingPercentage = matchingPercentage;
        this.id = id;
        this.interests = interests;
        this.displayName = displayName;
        this.major=major;
        this.university=university;
    }

    public int getMatchingPercentage() {
        return matchingPercentage;
    }

    public void setMatchingPercentage(int matchingPercentage) {
        this.matchingPercentage = matchingPercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String[] getInterests() {
        return interests;
    }

    public void setInterests( String[] interests) {
        this.interests = interests;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}