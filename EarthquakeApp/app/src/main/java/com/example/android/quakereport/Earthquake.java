package com.example.android.quakereport;

/**
 * Created by janda_000 on 2/16/2017.
 */

public class Earthquake {

    private double eMagnitude;
    private String eLocation;
    private long eDate;

    public Earthquake(double magnitude, String location, long timeInMS){
        eMagnitude = magnitude;
        eLocation = location;
        eDate = timeInMS;
    }

    public double geteMagnitude(){ return eMagnitude; }
    public String geteLocation(){ return eLocation; }
    public long geteDate(){ return eDate; }

}
