package org.algosketch.part6_mission3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ItemVO> list = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            if(i % 3 == 0) {
                HeaderVO vo = new HeaderVO();
                vo.title = "어제";
                list.add(vo);
            } else {
                DataVO vo = new DataVO("이름", "날짜" + i);
                list.add(vo);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(list));
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration());
    }

    private class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if(parent.getChildViewHolder(view).getItemViewType() == ItemVO.TYPE_DATA) {
                outRect.set(16, 16, 16, 16);
                view.setBackgroundColor(0xFFFFFFFF);
                ViewCompat.setElevation(view, 20.0f);
            }
        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<ItemVO> list;

        public RecyclerViewAdapter(List<ItemVO> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(viewType == ItemVO.TYPE_HEADER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_header, parent, false);
                return new HeaderViewHolder(view);
            }

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_data, parent, false);
            return new DataViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ItemVO vo = list.get(position);
            if(vo.getType() == ItemVO.TYPE_HEADER) {
                ((HeaderViewHolder)holder).title.setText(((HeaderVO)vo).title);
            } else {
                ((DataViewHolder)holder).name.setText(((DataVO)vo).name);
                ((DataViewHolder)holder).date.setText(((DataVO)vo).date);
            }
        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }
    }

    private class DataViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;

        public DataViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.mission3_item_name);
            this.date = view.findViewById(R.id.mission3_item_date);
        }
    }

    private class HeaderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        public TextView title;

        public HeaderViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.mission3_item_header);
        }
    }

    abstract class ItemVO {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_DATA = 1;
        abstract int getType();
    }

    private class HeaderVO extends ItemVO {
        public String title;

        @Override
        public int getType() {
            return TYPE_HEADER;
        }
    }

    private class DataVO extends ItemVO {
        public String name;
        public String date;

        public DataVO(String name, String date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public int getType() {
            return TYPE_DATA;
        }
    }
}