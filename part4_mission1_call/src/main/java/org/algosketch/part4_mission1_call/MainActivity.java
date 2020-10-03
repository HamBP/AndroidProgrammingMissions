package org.algosketch.part4_mission1_call;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView_call);
        DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_call order by _id asc", null);

        ArrayList<CallVO> datas = new ArrayList<>();
        while(cursor.moveToNext()) {
            CallVO vo = new CallVO();
            vo.photo = cursor.getInt(1);
            vo.name = cursor.getString(2);
            vo.phoneType = cursor.getString(3); // 이름
            vo.date = cursor.getString(4); // 휴대폰
            vo.phone = cursor.getString(5);
            datas.add(vo);
        }
        db.close();
        cursor.close();

        CallAdapter callAdapter = new CallAdapter(this, R.layout.call_list_item, datas);
        listView.setAdapter(callAdapter);
    }
}