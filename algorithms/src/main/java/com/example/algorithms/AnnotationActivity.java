package com.example.algorithms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.algorithms.annotations.AnnotationUtil;
import com.example.algorithms.annotations.ContentView;
import com.example.algorithms.annotations.InjectView;
import com.example.algorithms.annotations.OnClick;

import butterknife.BindView;

@ContentView(R.layout.activity_annotation)
public class AnnotationActivity extends AppCompatActivity {

    @InjectView(R.id.btn1)
    Button button1;

    @BindView(R.id.btn3)
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationUtil.inject(this);
        button1.setText("11111");
    }

    @OnClick({R.id.btn2})
    public void handler(View view) {
        Toast.makeText(this, "" + view.getId() + "我被点击了", Toast.LENGTH_SHORT).show();
    }
}
