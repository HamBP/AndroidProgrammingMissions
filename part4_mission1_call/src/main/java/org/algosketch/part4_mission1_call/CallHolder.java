package org.algosketch.part4_mission1_call;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CallHolder {
    ImageView profileImageView;
    TextView nameView;
    TextView dateView;
    ImageView callView;

    public CallHolder(View root) {
        profileImageView = root.findViewById(R.id.img_profile);
        nameView = root.findViewById(R.id.text_name);
        dateView = root.findViewById(R.id.text_date);
        callView = root.findViewById(R.id.img_dialer);
    }
}
