package com.example.part3_mission1_address_book;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, "email_address", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table tb_addresses " +
                "(_id integer primary key autoincrement, " +
                "name, " +
                "phone, " +
                "email)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            sqLiteDatabase.execSQL("drop table tb_addresses");
            onCreate(sqLiteDatabase);
        }
    }
}
