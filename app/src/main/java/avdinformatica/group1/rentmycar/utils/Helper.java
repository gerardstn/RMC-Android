package avdinformatica.group1.rentmycar.utils;

import android.location.Location;

import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.UUID;

public class Helper {
    public static String[] getLatLong(String coordinates) {
        String[] latLong = coordinates.split(",");
        return latLong;
    }

    public static double getLat(String coordinates) {
        String[] latLong = coordinates.split(",");
        return Double.parseDouble(latLong[0]);
    }

    public static double getLong(String coordinates) {
        String[] latLong = coordinates.split(",");
        return Double.parseDouble(latLong[1]);
    }

    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat().parse(date);
    }

    /* Returns the units in meters. */
    public static int calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        float[] results = new float[3];
        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
        return (int) (results[0] / 1000);
    }

    public static String generateRandomSessionString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
