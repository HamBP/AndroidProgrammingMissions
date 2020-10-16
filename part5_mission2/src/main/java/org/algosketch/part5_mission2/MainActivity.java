package org.algosketch.part5_mission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView callBtn;
    ImageView mapBtn;
    ImageView internetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callBtn = findViewById(R.id.mission2_call);
        mapBtn = findViewById(R.id.mission2_location);
        internetBtn = findViewById(R.id.mission2_internet);

        callBtn.setOnClickListener(new ClickCallBtn());
        mapBtn.setOnClickListener(new ClickMapBtn());
        internetBtn.setOnClickListener(new ClickInternetBtn());
    }

    class ClickCallBtn implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions((Activity)view.getContext(), new String[] {Manifest.permission.CALL_PHONE}, 1);
            }
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:02120"));
            startActivity(intent);
        }
    }

    class ClickMapBtn implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952,126.9779451?q=37.5662952,126.9779451"));
            startActivity(intent);
        }
    }

    class ClickInternetBtn implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
            startActivity(intent);
        }
    }
}