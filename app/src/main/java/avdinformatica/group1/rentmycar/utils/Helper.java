package avdinformatica.group1.rentmycar.utils;

import java.lang.*;
import java.util.*;

public class Helper {
    public static String[] getLatLong(String coordinates)
    {
        String[] latLong = coordinates.split(",");
        return latLong;
    }

    public static String getLat(String coordinates)
    {
        String[] latLong = coordinates.split(",");
        return latLong[0];
    }

    public static String getLong(String coordinates)
    {
        String[] latLong = coordinates.split(",");
        return latLong[1];
    }
}
