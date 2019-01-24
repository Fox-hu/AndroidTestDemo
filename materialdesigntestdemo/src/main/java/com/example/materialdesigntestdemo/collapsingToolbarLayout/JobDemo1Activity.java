package com.example.materialdesigntestdemo.collapsingToolbarLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.materialdesigntestdemo.R;

import java.util.Arrays;
import java.util.List;


public class JobDemo1Activity extends AppCompatActivity {
    private static final String TAG = JobDemo1Activity.class.getSimpleName();
    private NestedScrollView Low;
    private RecyclerView rv;
    private String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo1);
        Low = findViewById(R.id.low_scrollview);
        Low.setOnScrollChangeListener(lowListener);

        rv = findViewById(R.id.rv_content);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TestAdapter(R.layout.rv_item, Arrays.asList(data)));
    }

    private NestedScrollView.OnScrollChangeListener lowListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                int oldScrollY) {
            Log.e(TAG, "low NestedScrollView onScrollChange ," + "getMaxScrollAmount = " +
                       v.getMaxScrollAmount() + "scrollX = " + scrollX + "scrollY = " + scrollY +
                       "oldScrollX = " + oldScrollX + "oldScrollY = " + oldScrollY);
        }
    };


    private class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TestAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        public TestAdapter(@Nullable List<String> data) {
            super(data);
        }

        public TestAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.hot_word_textview, item);
        }
    }
}
