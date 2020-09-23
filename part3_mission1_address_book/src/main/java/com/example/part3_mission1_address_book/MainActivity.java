package com.example.part3_mission1_address_book;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    TextView nameView;
    TextView phoneView;
    TextView emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.btn_add);
        nameView = findViewById(R.id.input_name);
        phoneView = findViewById(R.id.input_phone_number);
        emailView = findViewById(R.id.input_email_address);

        addBtn.setOnClickListener(new OnClick());
    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String name = nameView.getText().toString();
            String phone = phoneView.getText().toString();
            String email = emailView.getText().toString();

            DBHelper helper = new DBHelper(MainActivity.this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("insert into tb_addresses (name, phone, email) values (?, ?, ?)",
                    new String[] {name, phone, email});
            db.close();

            Intent intent = new Intent(MainActivity.this, ReadAdressesActivity.class);
            startActivity(intent);
        }
    }
}
