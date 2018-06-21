package com.example.materialdesigntestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.materialdesigntestdemo.collapsingToolbarLayout.CollapsingNormalActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.ScrollingActivity;
import com.example.materialdesigntestdemo.collapsingToolbarLayout.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private Map<String, Class<?>> activityMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    startActivity(new Intent(MainActivity.this, activityMap.get(s)));
                }
            }
        });
    }

    private void initItem() {
        activityMap.put("CollapsingNormalActivity", CollapsingNormalActivity.class);
        activityMap.put("ScrollingActivity", ScrollingActivity.class);
        activityMap.put("SearchActivity", SearchActivity.class);
    }


}
