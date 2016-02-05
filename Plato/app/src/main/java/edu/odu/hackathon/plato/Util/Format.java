package edu.odu.hackathon.plato.Util;

import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by alexander.dohrn on 11/19/15.
 */
public class Format {

    String TAG = "Format";
    public static final long AVERAGE_MONTH_IN_MILLIS = DateUtils.DAY_IN_MILLIS * 30;

    public static String timeStampToDateString(String stamp) {

        String formatString = "";

        if (stamp != null) {

            String s = stamp.replace("Z", "+00:00");
            try {
                s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            } catch (IndexOutOfBoundsException e) {

            }

            Date date = new Date();

            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ").parse(s);
                formatString = new SimpleDateFormat("MM/dd/yy").format(date);
            } catch (ParseException e) {
                // Error parsing date. Return Timestamp
                return stamp;
            }
        }

        return formatString;
    }

    public static String timeStampToCurrentDateString(String stamp) {
        if (stamp != null && !stamp.equals("")) {
            String s = stamp.replace("Z", "+00:00");
            try {
                s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            } catch (IndexOutOfBoundsException e) {

            }

            Date date = new Date();
            Date current = new Date();

            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ").parse(s);
            } catch (ParseException e) {
                //Error parsing date. Return timestamp instead.
                return stamp;
            }

            return getFuzzyTime(date.getTime());
        }
        return "";
    }


    public static String getFuzzyTime(long time) {
        final long now = new Date().getTime();
        final long delta = now - time;
        long resolution;
        if (delta <= DateUtils.MINUTE_IN_MILLIS) {
            resolution = DateUtils.SECOND_IN_MILLIS;
        } else if (delta <= DateUtils.HOUR_IN_MILLIS) {
            resolution = DateUtils.MINUTE_IN_MILLIS;
        } else if (delta <= DateUtils.DAY_IN_MILLIS) {
            resolution = DateUtils.HOUR_IN_MILLIS;
        } else if (delta <= DateUtils.WEEK_IN_MILLIS) {
            resolution = DateUtils.DAY_IN_MILLIS;
        } else if (delta <= AVERAGE_MONTH_IN_MILLIS) {
            long period = delta / DateUtils.WEEK_IN_MILLIS;
            if (period == 1) {
                return "1 week ago";
            }
            return Integer.toString((int) (period)) + " weeks ago";
        } else if (delta <= DateUtils.YEAR_IN_MILLIS) {
            long period = delta / AVERAGE_MONTH_IN_MILLIS;
            if (period == 1) {
                return "1 month ago";
            }
            return Integer.toString((int) (period)) + " months ago";
        } else {
            long period = delta / DateUtils.YEAR_IN_MILLIS;
            if (period == 1) {
                return "1 year ago";
            }
            return Integer.toString((int) (period)) + " years ago";
        }
        return DateUtils.getRelativeTimeSpanString(time, now, resolution).toString();
        }

    public static String cleanPhoneNumber(String number) {
        //Make sure the phone number is long enough
        if (number.length() == 12) {
            number = number.replace("+1", "");
            number = number.substring(0, 3) + "-" + number.substring(3, 6) + "-" + number.substring(6);
            return number;
        } else
            throw new IllegalArgumentException("String must be 12 characters in the format '+1XXXXXXXXXX'");
    }

    public static String phoneNumberToAPIFormat(String number) {
        number = number.replace("-", "");
        number = number.replace(".", "");
        number = number.replace("(", "");
        number = number.replace(")", "");
        number = "+1" + number;

        return number;
    }

    public static String calendarToISO8601(Calendar cal) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
        df.setTimeZone(tz);
        String timestamp = df.format(cal.getTimeInMillis());
        timestamp = timestamp.substring(0, timestamp.indexOf('Z') + 1);

        return timestamp;
    }
}
