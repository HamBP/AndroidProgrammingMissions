package org.algosketch.part6_mission3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor todayCursor = db.rawQuery("select * from tb_data where date = '2019-07-01'", null);
        Cursor yesterdayCursor = db.rawQuery("select * from tb_data where date = '2019-06-30'", null);
        Cursor beforeCursor = db.rawQuery("select * from tb_data where date != '2019-07-01' and date != '2019-06-30'", null);

        List<ItemVO> list = new ArrayList<>();
        HeaderVO vo = new HeaderVO();
        vo.title = "오늘";
        list.add(vo);
        while(todayCursor.moveToNext()) {
            String name = todayCursor.getString(1);
            String date = todayCursor.getString(2);
            DataVO dvo = new DataVO(name, date);
            list.add(dvo);
        }
        vo = new HeaderVO();
        vo.title = "어제";
        list.add(vo);
        while(yesterdayCursor.moveToNext()) {
            String name = yesterdayCursor.getString(1);
            String date = yesterdayCursor.getString(2);
            DataVO dvo = new DataVO(name, date);
            list.add(dvo);
        }
        vo = new HeaderVO();
        vo.title = "이전";
        list.add(vo);
        while(beforeCursor.moveToNext()) {
            String name = beforeCursor.getString(1);
            String date = beforeCursor.getString(2);
            DataVO dvo = new DataVO(name, date);
            list.add(dvo);
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
                int rand = new Random().nextInt(6);
                switch (rand) {
                    case 1:
                        ((GradientDrawable)view.findViewById(R.id.mission3__item_person).getBackground()).setColor(0xFFFE2E2E);
                        break;
                    case 2:
                        ((GradientDrawable)view.findViewById(R.id.mission3__item_person).getBackground()).setColor(0xFFFF8000);
                        break;
                    case 3:
                        ((GradientDrawable)view.findViewById(R.id.mission3__item_person).getBackground()).setColor(0xFFF7FE2E);
                        break;
                    case 4:
                        ((GradientDrawable)view.findViewById(R.id.mission3__item_person).getBackground()).setColor(0xFF2EFE2E);
                        break;
                    case 5:
                        ((GradientDrawable)view.findViewById(R.id.mission3__item_person).getBackground()).setColor(0xFF2EFEF7);
                        break;
                    default:
                }
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