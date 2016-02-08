package com.example.wilsonsu.instagramclient;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by weishengsu on 2/7/16.
 */
public class UnitConverter {
    public static String converTimetoRelativeTime(Date time) {
        String relativeDate = DateUtils.getRelativeTimeSpanString(time.getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_TIME).toString();
        relativeDate.replaceFirst("hour", "h");
        relativeDate.replaceFirst("minute", "min");
        return relativeDate;
    }

    public static String numberConverter(int n) {
        String text =  (n > 1000 ? n / 1000 + "k" : n + "");
        return text;
    }


}
