package org.algosketch.part4_mission1_call;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    public DBhelper(Context context) {
        super(context, "callDB", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "create table tb_call" +
                "(_id integer primary key autoincrement," +
                "photo integer," +
                "name," +
                "phoneType," +
                "date," +
                "phone)";
        db.execSQL(createQuery);

        db.execSQL("insert into tb_call (photo, name, phoneType, date, phone) values (1, '송준영', '휴대폰', '1일전', '010001')");
        db.execSQL("insert into tb_call (photo, name, phoneType, date, phone) values (0, '홍홍홍', '휴대폰', '2일전', '010001')");
        db.execSQL("insert into tb_call (photo, name, phoneType, date, phone) values (0, '송송송', '휴대폰', '3일전', '010001')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(DB_VERSION == newVersion) {
            db.execSQL("drop table tb_call");
            onCreate(db);
        }
    }
}
