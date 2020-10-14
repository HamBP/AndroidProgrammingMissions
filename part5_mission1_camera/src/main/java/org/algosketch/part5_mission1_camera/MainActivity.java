package org.algosketch.part5_mission1_camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView addPhotoBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPhotoBtn = findViewById(R.id.main_photo_icon);
        addPhotoBtn.setOnClickListener(new AddPhotoBtnOnClickListener());
    }

    class AddPhotoBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String[] strings = {"사진 촬영", "동영상 촬영"};

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("촬영")
                    .setItems(strings, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                        }
                    });
            builder.show();
        }
    }
}
