package com.thirudetails.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

import static android.content.Context.MODE_PRIVATE;

public class Constants {


    public static final String MY_PREFS = "pref";

    public static final String NAME  = "name";
    public static final String YOU_IMAGE  = "you_image";
    public static final String CITY  = "city";
    public static final String GENDER  = "gender";
    public static final String PHONE  = "phone";
    public static final String EMAIL  = "email";
    public static final String PASSWORD = "password";


    public static final String VIDEO_PATH = "video_path";
    public static final String SENT_IMAGES = "Sent_Images";
    public static final String STATUS_IMAGES = "Status_Images";
    public static final String STATUS_VIDEOS = "Status_Videos";
    public static final String SAVED_FILES = "Saved_files";
    public static final String RATE_REVIEW = "Rate_review";

    public static final String MY_PREFERENCES = "MY_PREFERENCES";
    public static final String FIRST_INSTALL = "FIRST_INSTALL";
    public static final String POSITION = "POSITION";
    public static final String KEY_ARRAY_IMAGE = "Key_array";
    public static final String SHARE_WHATSAPP = "Share on Whatsapp";
    public static final String SHARE = "Share";
    public static final String SAVE = "Save";
    public static final String SENT_VIDEOS = "Sent_Videos";
    public static final String SENT_GIFS = "Sent_Gifs";
    public static final String CALL_BACK = "call_back";


    public static boolean isFirstRun(Context context, String TAG) {
        SharedPreferences firstPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isFirst = firstPref.getBoolean(TAG, true);
        if (isFirst) {

            SharedPreferences.Editor editor = firstPref.edit();
            editor.putBoolean(TAG, false);
            editor.apply();
            return true;
        }
        return false;
    }

    public static void setPrefernce(Context context, String TAG, String value) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TAG, value);
        editor.apply();
    }

    public static String getPrefernce(Context context, String TAG) {

        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        return preferences.getString(TAG, "");
    }

    public static void clearAllPrefernces(Context context) {
        System.out.println("Contextclear=" + context);
        /*if(Constants.clearing_context!=null)
            context=Constants.clearing_context;*/

        if (context != null) {
            SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            editor.commit();
        }
    }



}
