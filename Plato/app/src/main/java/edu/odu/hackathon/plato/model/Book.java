package edu.odu.hackathon.plato.model;

import android.util.Log;

/**
 * Created by Handson_2 on 2/4/2016.
 */
public class Book {

    String TAG = "";
    public String displayName;
    public String userId;
    public String[] interests;
    public String books;
    public String major;
    public String university;
    public int matchingPercentage;

    public Book() {
        Log.v(TAG,"Started");
    }


    public Book(int matchingPercentage, String displayName, String userId, String books) {
        Log.v(TAG, "Started");
        this.matchingPercentage = matchingPercentage;
        this.displayName = displayName;
        this.userId = userId;
        this.books = books;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public int getMatchingPercentage() {
        return matchingPercentage;
    }

    public void setMatchingPercentage(int matchingPercentage) {
        this.matchingPercentage = matchingPercentage;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
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
