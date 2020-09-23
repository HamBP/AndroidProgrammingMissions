package com.example.part3_mission1_address_book;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReadAdressesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_adresses);

        TextView nameView = findViewById(R.id.output_name);
        TextView phoneView = findViewById(R.id.output_phone_number);
        TextView emailView = findViewById(R.id.output_email_address);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, phone, email from tb_addresses order by _id asc", null);
        while(cursor.moveToNext()) {
            nameView.setText(cursor.getString(0));
            phoneView.setText(cursor.getString(1));
            emailView.setText(cursor.getString(2));
        }
        db.close();
        cursor.close();
    }
}