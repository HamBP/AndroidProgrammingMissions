package org.algosketch.part5_mission1_camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView addPhotoBtn;
    File filePath;
    //ImageView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPhotoBtn = findViewById(R.id.main_photo_icon);
        //resultView = findViewById(R.id.result_view);
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
                            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                if(true) { // 사진
                                    try{
                                        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                                        File dir = new File(dirPath);
                                        if(!dir.exists()) {
                                            dir.mkdir();
                                        }
                                        Toast.makeText(MainActivity.this, "11", Toast.LENGTH_SHORT).show();

                                        filePath = File.createTempFile("IMG", ".jpg", dir);
                                        if(!filePath.exists()) {
                                            filePath.createNewFile();
                                        }
                                        Uri photoURI = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", filePath);
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                        startActivityForResult(intent, 4);
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if(which == 1) { // 동영상

                                }
                            } else { // 퍼미션이 없을 때
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                            }
                        }
                    });
            builder.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 4 && resultCode == RESULT_OK) {
            if(filePath != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                try {
                    InputStream in = new FileInputStream(filePath);
                    BitmapFactory.decodeStream(in, null, options);
                    in.close();
                    in = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BitmapFactory.Options imgOptions = new BitmapFactory.Options();
                imgOptions.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), imgOptions);
                //resultView.setImageBitmap(bitmap);
            }
        } else if(requestCode == 8 && resultCode == RESULT_OK) {

        }
    }
}
