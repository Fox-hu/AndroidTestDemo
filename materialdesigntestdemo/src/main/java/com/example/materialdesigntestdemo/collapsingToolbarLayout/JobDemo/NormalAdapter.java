package com.example.materialdesigntestdemo.collapsingToolbarLayout.JobDemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.materialdesigntestdemo.R;

import java.util.List;

/**
 * Created by fox on 2019/1/25.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView title;

        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.card_text);
        }
    }

    private List<String> mDatas;

    public NormalAdapter(List<String> data) {
        this.mDatas = data;
    }

    @Override
    public void onBindViewHolder(NormalAdapter.VH holder, int position) {
        holder.title.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public NormalAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent,
                false);
        return new NormalAdapter.VH(v);
    }

}
