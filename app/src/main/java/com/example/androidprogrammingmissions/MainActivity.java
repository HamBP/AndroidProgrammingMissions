package com.example.androidprogrammingmissions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button verificationBtn;
    Button settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificationBtn = findViewById(R.id.verificationBtn);
        settingBtn = findViewById(R.id.settingBtn);

        verificationBtn.setOnClickListener(new ClickEvent());
        settingBtn.setOnClickListener(new ClickEvent());
    }

    class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == verificationBtn) {
                showToast(getString(R.string.ok) + " button is clicked");
            }
            else if(view == settingBtn) {
                showToast(getString(R.string.ok) + " button is clicked");
            }
        }
    }

    void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}