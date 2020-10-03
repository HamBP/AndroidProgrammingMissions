package org.algosketch.part4_mission1_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CallAdapter extends ArrayAdapter<CallVO> {
    Context context;
    int resId;
    ArrayList<CallVO> datas;

    public CallAdapter(Context context, int resId, ArrayList<CallVO> datas) {
        super(context, resId);
        this.resId = resId;
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resId, null);

        if(convertView == null) {
            ImageView profileImageView = convertView.findViewById(R.id.img_profile);
            TextView nameView = convertView.findViewById(R.id.text_name);
            TextView dateView = convertView.findViewById(R.id.text_date);
            ImageView callView = convertView.findViewById(R.id.img_dialer);
        }
        final CallVO vo = datas.get(position);

        if(vo.photo) profileImageView.setImageResource(R.drawable.hong);
        else profileImageView.setImageResource(R.drawable.ic_person);
        nameView.setText(vo.name);
        dateView.setText(vo.phoneType + ", " + vo.date);

        callView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : 전화 걸기
            }
        });

        return convertView;
    }
}
