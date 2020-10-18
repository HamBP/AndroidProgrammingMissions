package org.algosketch.part6_mission1;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);

        List<String> list = new ArrayList<>();
        for(int i = 1; i <= 20; ++i) {
            list.add("Item " + i);
        }

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RecyclerViewAdapter(list));
        recyclerView.addItemDecoration(new RecyclerViewDecoration());

        return view;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        List<String> list;
        public RecyclerViewAdapter(List<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            String text = list.get(position);
            holder.title.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(android.R.id.text1);
        }
    }

    class RecyclerViewDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.set(20, 20, 20, 20);
            view.setBackgroundColor(0xFFECE9E9);
            ViewCompat.setElevation(view, 20.0f);
        }
    }
}
