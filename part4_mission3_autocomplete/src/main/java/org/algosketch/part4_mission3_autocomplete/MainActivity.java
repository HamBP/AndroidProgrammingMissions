package org.algosketch.part4_mission3_autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ListView listView;
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String> dummyData = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        listView = findViewById(R.id.list_view);

        editText.addTextChangedListener(new EditTextListener());
        listView.setOnItemClickListener(new ListViewOnItemClickListener());

        dummyData.add("안녕하세요"); dummyData.add("안녕하신가요"); dummyData.add("인성 문제 있어?");

        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(arrayAdapter);
    }

    class EditTextListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence str, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence str, int start, int count, int after) {
            String inputString = editText.getText().toString();
            if(inputString.isEmpty()) strings.clear();
            else setStrings(inputString);
            listView.setAdapter(arrayAdapter);
        }

        @Override
        public void afterTextChanged(Editable str) {

        }
    }

    class ListViewOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            editText.setText(strings.get(position));
        }
    }

    private void setStrings(String str) {
        strings.clear();
        for(int i = 0; i < dummyData.size(); ++i) {
            if(dummyData.get(i).indexOf(str) != -1) {
                strings.add(dummyData.get(i));
            }
        }
    }
}
