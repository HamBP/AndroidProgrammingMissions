package org.algosketch.part6_mission3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<CallDocumentVO> list = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            CallDocumentVO vo = new CallDocumentVO("이름", "날짜" + i);
            list.add(vo);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(list));
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        List<CallDocumentVO> list;

        public RecyclerViewAdapter(List<CallDocumentVO> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_data, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            CallDocumentVO vo = list.get(position);
            holder.name.setText(vo.name);
            holder.date.setText(vo.date);
        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }
    }

    private class RecyclerViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TextView name;
        TextView date;

        public RecyclerViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.mission3_item_name);
            date = view.findViewById(R.id.mission3_item_date);
        }
    }

    private class CallDocumentVO {
        private String name;
        private String date;

        public CallDocumentVO(String name, String date) {
            this.name = name;
            this.date = date;
        }
    }
}