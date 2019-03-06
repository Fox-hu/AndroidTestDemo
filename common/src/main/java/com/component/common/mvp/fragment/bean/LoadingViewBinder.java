package com.component.common.mvp.fragment.bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.component.common.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Meiji on 2017/6/8.
 */

public class LoadingViewBinder extends ItemViewBinder<LoadingBean, LoadingViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected LoadingViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.common_item_loading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LoadingBean item) {
//        holder.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#000000"),
//                PorterDuff.Mode.SRC_IN);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            this.progressBar = itemView.findViewById(R.id.progress_footer);
        }
    }
}
