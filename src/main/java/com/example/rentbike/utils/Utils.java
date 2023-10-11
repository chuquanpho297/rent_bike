package com.example.rentbike.utils;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.logging.Logger;

public class Utils {

    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Logger LOGGER = getLogger(Utils.class.getName());

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
    }

    public static Logger getLogger(String className) {
        return Logger.getLogger(className);
    }
    
    /**
     * Convert time to string
     * @param time
     * @return
     */
    public static String convertTime(int time) {
        int hours = (int) time / 3600;
        int minutes = (int) (time - hours * 3600) / 60;
        if (minutes == 0) return hours + " hours";
        if (hours == 0) return minutes + " minutes";
        return hours + " hours " + minutes + " minutes";
    }

    /**
     * make a currency in vietnam format
     * @return money in vietnam currency
     */
    public static String getCurrencyFormat(int num) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietnam);
        return defaultFormat.format(num);
    }

    /**
     * Return a {@link java.lang.String String} that represents the current time in the format of yyyy-MM-dd HH:mm:ss.
     *
     * @return the current time as {@link java.lang.String String}.
     */
    public static Timestamp getCurrentTime() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static Integer getBikeDeposit(Integer value){
        return (int) (value * 0.4);
    }
}