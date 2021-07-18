package com.example.android.quakereport;

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private long mTimeInMillisecond;
    private String mUrl;

    public Earthquake(Double Magnitude, String Location, long TimeInMillisecond, String url)
    {
        mMagnitude = Magnitude;
        mLocation = Location;
        mTimeInMillisecond = TimeInMillisecond;
        mUrl = url;
    }

    public Double getMagnitude()
    {
        return mMagnitude;
    }
    public String getLocation()
    {
        return mLocation;
    }
    public String getUrl()
    {
        return mUrl;
    }
    public long getTimeInMillisecond(){return mTimeInMillisecond;}
}
