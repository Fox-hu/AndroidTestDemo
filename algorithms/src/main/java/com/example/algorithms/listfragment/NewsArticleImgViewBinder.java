package com.example.algorithms.listfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Meiji on 2017/6/8.
 * 带图片的 item
 */

public class NewsArticleImgViewBinder extends ItemViewBinder<MultiNewsArticleDataBean, NewsArticleImgViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected NewsArticleImgViewBinder.ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.test_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NewsArticleImgViewBinder.ViewHolder holder,
            @NonNull final MultiNewsArticleDataBean item) {
        holder.tv_title.setText("image");
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;

        ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(android.R.id.text1);
        }
    }
}
