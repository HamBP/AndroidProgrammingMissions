package org.algosketch.part4_mission2_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    CoolProgressBar coolProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seek_bar);
        coolProgressBar = findViewById(R.id.coolBar);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener());
    }

    class OnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            coolProgressBar.onChange(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
