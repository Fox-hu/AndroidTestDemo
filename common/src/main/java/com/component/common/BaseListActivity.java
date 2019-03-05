package com.component.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class BaseListActivity extends AppCompatActivity {

    private ListView lv;
    protected Map<String, Class<?>> activityMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity_main);
        lv = findViewById(R.id.lv);

        initItem();

        ArrayAdapter activityAdatper = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>(activityMap.keySet()));
        lv.setAdapter(activityAdatper);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (view instanceof TextView) {
                    String s = ((TextView) view).getText().toString();
                    startActivity(new Intent(BaseListActivity.this, activityMap.get(s)));
                }
            }
        });

    }

    protected abstract void initItem();
}
