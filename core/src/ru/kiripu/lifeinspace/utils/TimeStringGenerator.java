package ru.kiripu.lifeinspace.utils;

/**
 * Created by kiripu on 18.01.2016.
 */
public class TimeStringGenerator {
    public static String generateTimeString(int value)
    {
        int time = value;
        int min = time / 60;
        int sec = time % 60;
        String minString = (min < 10) ? "0" + min : "" + min;
        String secString = (sec < 10) ? "0" + sec : "" + sec;
        return minString + ":" + secString;
    }
}
