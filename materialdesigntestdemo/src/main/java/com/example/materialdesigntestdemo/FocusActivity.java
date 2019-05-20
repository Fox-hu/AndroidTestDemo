package com.example.materialdesigntestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.component.common.utils.Utils;

import java.util.Arrays;

public class FocusActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        recyclerView = findViewById(R.id.rv_content);
        Utils.setRv(recyclerView,this);
        recyclerView.setAdapter(new EditAdapter(R.layout.rv_ed_item, Arrays.asList(Utils.data)));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
