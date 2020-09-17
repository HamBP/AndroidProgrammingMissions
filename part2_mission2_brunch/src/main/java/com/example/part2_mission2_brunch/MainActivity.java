package com.example.part2_mission2_brunch;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView closeBtn;
    ImageView finishBtn;
    ImageView photoBtn;
    ImageView textColorBtn;
    ImageView textAlignBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        closeBtn = findViewById(R.id.close_btn);
        finishBtn = findViewById(R.id.finish_btn);
        photoBtn = findViewById(R.id.photo_btn);
        textColorBtn = findViewById(R.id.text_color_btn);
        textAlignBtn = findViewById(R.id.text_align_btn);

        closeBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        photoBtn.setOnClickListener(this);
        textColorBtn.setOnClickListener(this);
        textAlignBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        showToast("원래는 이 이미지를 터치하면 각각 어떤 이미지가 눌렸는지 처리를 해야하지만 그러기엔 내가 귀찮아서 안 했으니 이 화면을 보고있는 네가 이해해줘");
    }

    void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
