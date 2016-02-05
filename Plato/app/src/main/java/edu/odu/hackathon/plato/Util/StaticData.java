package edu.odu.hackathon.plato.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kahmed on 2/5/16.
 */
public class StaticData {


    public static ArrayList<String> interestList = new ArrayList<>(Arrays.asList("Javascript",
            "JQuery",
            "HTML5",
            "AngularJS",
            "ReactJS",
            "C",
            "C++",
            "Java",
            "Asp.NET",
            "Oracle",
            "MySQL",
            "MongoDB",
            "CouchDB",
            "Android",
            "Objective-C",
            "Linux",
            "Animation",
            "Graphic Design",
            "UI Design",
            "Weight Loss",
            "Workouts",
            "Diet",
            "Paleo Diet",
            "Healthy Eating",
            "Photography",
            "Wildlife Photography",
            "Nature Photography",
            "Fashion Photography",
            "Travel Photography"));

    public static ArrayList<String> getInterestList() {
        return interestList;
    }

    public static void addInterest(String interest) {
        StaticData.interestList.add(interest);
    }

}
