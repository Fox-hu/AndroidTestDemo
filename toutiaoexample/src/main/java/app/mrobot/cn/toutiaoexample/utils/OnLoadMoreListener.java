package app.mrobot.cn.toutiaoexample.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by fox on 2018/2/27.
 * http://www.loongwind.com/archives/189.html
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private static final String TAG = OnLoadMoreListener.class.getSimpleName();

    private LinearLayoutManager layoutManager;
    private int itemCount;
    private int lastPosition;
    private int lastItemCount;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            itemCount = layoutManager.getItemCount();
            lastPosition = layoutManager
                    .findLastCompletelyVisibleItemPosition();
        } else {
            Log.e(TAG,"The OnLoadMoreListener only support LinearLayoutManager");
        }
        
        if(lastItemCount != itemCount && lastPosition == itemCount -1){
            lastItemCount = itemCount;
            onLoadMore();
        }
    }

    /**
     * 在滑动读取更多时，返回的ｃｏｕｎｔ不等于当前的ｃｏｕｎｔ，并且已是最后一项
     */
    protected abstract void onLoadMore();
}
