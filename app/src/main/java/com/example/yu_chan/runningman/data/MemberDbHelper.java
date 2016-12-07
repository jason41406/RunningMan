package com.example.yu_chan.runningman.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yu_chan.runningman.data.MemberContract.MemberEntry;
/**
 * Created by Yu_Chan on 2016/11/3.
 */
public class MemberDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MemberDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "runningman.db";

    private static final int DATABASE_VERSION = 1;

    public MemberDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_MEMBERS_TABLE =  "CREATE TABLE " + MemberEntry.TABLE_NAME + " ("
                + MemberEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MemberEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + MemberEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + MemberEntry.COLUMN_AGE + " INTEGER NOT NULL, "
                + MemberEntry.COLUMN_HEIGHT + " INTEGER NOT NULL, "
                + MemberEntry.COLUMN_WEIGHT + " INTEGER NOT NULL, "
                + MemberEntry.COLUMN_ACCOUNT + " TEXT NOT NULL, "
                + MemberEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_MEMBERS_TABLE);
//          db.execSQL( "CREATE TABLE memebers(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, " +
//                  "gender INTEGER NOT NULL, age INTEGER NOT NULL, height INTEGER NOT NULL, " +
//                  "weight INTEGER NOT NULL, weight INTEGER NOT NULL, account TEXT NOT NULL, " +
//                  "password TEXT NOT NULL) ");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ){

    }
}
