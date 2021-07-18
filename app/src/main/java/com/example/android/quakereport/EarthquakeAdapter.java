package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPERATOR = " of ";
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0,  earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedMagnitude = decimalFormat.format(currentEarthquake.getMagnitude());
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        Date dateObject = new Date(currentEarthquake.getTimeInMillisecond());

        String originalLocation = currentEarthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        if(originalLocation.contains(LOCATION_SEPERATOR))
        {
            String[] parts = originalLocation.split(LOCATION_SEPERATOR);
            locationOffset = parts[0] + LOCATION_SEPERATOR;
            primaryLocation = parts[1];
        }
        else
        {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.location_primary);
        primaryLocationView.setText(primaryLocation);

        TextView offsetLocationView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetLocationView.setText(locationOffset);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        String formattedDate = formateDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeview = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formattime(dateObject);
        timeview.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String formattime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formateDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
