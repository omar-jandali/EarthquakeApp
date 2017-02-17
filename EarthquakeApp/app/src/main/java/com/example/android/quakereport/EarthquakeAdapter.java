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

/**
 * Created by janda_000 on 2/16/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPERATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        String primaryLocation;
        String locationOffset;

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_item, parent, false
            );
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.location);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        String originalLocation = currentEarthquake.geteLocation();
        Date dateObject = new Date(currentEarthquake.geteDate());
        String formattedMagnitude = formatMagnitude(currentEarthquake.geteMagnitude());
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);


        if (originalLocation.contains(LOCATION_SEPERATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPERATOR);
            locationOffset = parts[0] + LOCATION_SEPERATOR;
            primaryLocation = parts[1];
        }else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        magnitudeView.setText(formattedMagnitude);
        dateView.setText(formattedDate);
        timeView.setText(formattedTime);
        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

}
