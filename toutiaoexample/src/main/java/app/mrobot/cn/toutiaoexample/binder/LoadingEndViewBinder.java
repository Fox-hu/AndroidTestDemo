package app.mrobot.cn.toutiaoexample.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.LoadingEndBean;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by fox on 2018/2/27.
 */

public class LoadingEndViewBinder extends ItemViewBinder<LoadingEndBean,LoadingEndViewBinder.ViewHolder> {
    private static final String TAG = LoadingEndViewBinder.class.getSimpleName();

    @NonNull
    @Override
    protected LoadingEndViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loading_end, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull LoadingEndViewBinder.ViewHolder holder,
            @NonNull LoadingEndBean item) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
