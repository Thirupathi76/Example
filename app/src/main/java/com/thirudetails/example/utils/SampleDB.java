package com.thirudetails.example.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by user on 26-Sep-16.
 */
public class SampleDB extends SQLiteOpenHelper {

    public static SampleDB sdb;
    public static final String DB_NAME = "user";
    SQLiteDatabase sqldb;
    public static Context app_context;
    public static final String SAMPLE_TABLE = "user_details_table";



    public SampleDB(Context context) {
        super(context, DB_NAME, null, 1);

    }


    public static SampleDB getInstance(Context context) {
        app_context = context;
        if (sdb == null) {
            sdb = new SampleDB(context);
        }
        return sdb;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + SAMPLE_TABLE + "("

                + Constants.NAME + " TEXT,"
                /*+ Constants.YOU_IMAGE + " TEXT,"*/
                + Constants.CITY + " TEXT,"
                + Constants.GENDER + " TEXT,"
                + Constants.PHONE + " TEXT,"
                + Constants.PASSWORD + " TEXT,"
                + Constants.EMAIL + " TEXT"
                + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SAMPLE_TABLE);
        onCreate(sqLiteDatabase);
    }


    public void insertUserData(String name, /*String you_image,*/ String city, String gender,
                                   String phone, String email, String password) {

        sqldb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.NAME , name );
        /*values.put(Constants.YOU_IMAGE , you_image );*/
        values.put(Constants.CITY , city );
        values.put(Constants.GENDER , gender );
        values.put(Constants.PHONE , phone );
        values.put(Constants.EMAIL , email );
        values.put(Constants.PASSWORD ,  password);
        sqldb.insert(SAMPLE_TABLE, null, values);

    }


    public ArrayList<Map<String, String>> getRegisterData(String name) {

        ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SAMPLE_TABLE +" WHERE "+Constants.NAME+" = '" + name + "'",null);
        if (cursor != null && cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {
                Map<String, String> map = new HashMap<>();
                map.put(Constants.NAME , cursor.getString(cursor.getColumnIndex(Constants.NAME )));
                /*map.put(Constants.YOU_IMAGE , cursor.getString(cursor.getColumnIndex(Constants.YOU_IMAGE )));*/
                map.put(Constants.CITY , cursor.getString(cursor.getColumnIndex(Constants.CITY )));
                map.put(Constants.GENDER , cursor.getString(cursor.getColumnIndex(Constants.GENDER )));
                map.put(Constants.PHONE , cursor.getString(cursor.getColumnIndex(Constants.PHONE )));
                map.put(Constants.EMAIL , cursor.getString(cursor.getColumnIndex(Constants.EMAIL )));
                arrayList.add(map);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return arrayList;
    }


    public int validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + SAMPLE_TABLE +" WHERE "
                +Constants.EMAIL+"='" + username + "' AND "+Constants.PASSWORD+" = '" + password + "'",null);
        if (cursor != null && cursor.moveToFirst()) {
             count = cursor.getCount();
        }
        cursor.close();
        return count;
    }

    public String getUserName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String username;
        Cursor cursor = db.rawQuery("SELECT * FROM " + SAMPLE_TABLE +" WHERE "+Constants.EMAIL+"='" + email + "'",null);
        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex(Constants.NAME));
            return username;
        }
        cursor.close();
        return "";
    }
}
