package org.algosketch.part4_mission1_call;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            CallHolder holder = new CallHolder(convertView);
            convertView.setTag(holder);
        }
        CallHolder holder = (CallHolder) convertView.getTag();

        ImageView profileImageView = holder.profileImageView;
        TextView nameView = holder.nameView;
        TextView dateView = holder.dateView;
        ImageView callView = holder.callView;

        final CallVO vo = datas.get(position);

        if(vo.photo > 0) profileImageView.setImageResource(R.drawable.hong);
        else profileImageView.setImageResource(R.drawable.ic_person);
        nameView.setText(vo.name);
        dateView.setText(vo.phoneType + ", " + vo.date);

        callView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + vo.phone));
                if(ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((MainActivity)context, new String[]{Manifest.permission.CALL_PHONE}, 200);
                } else {
                    try {
                        view.getContext().startActivity(intent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return convertView;
    }
}
