package app.mrobot.cn.toutiaoexample.utils;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by fox on 2018/2/25.
 */

public class DiffCallback extends DiffUtil.Callback {
    private final Items mOldItems;
    private final Items mNewItems;

    public DiffCallback(Items mOldItems, Items mNewItems) {
        this.mOldItems = mOldItems;
        this.mNewItems = mNewItems;
    }

    public static void create(@NonNull Items oldList, @NonNull Items newList,
            @NonNull MultiTypeAdapter adapter) {
        DiffCallback diffCallback = new DiffCallback(oldList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
        diffResult.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return mOldItems == null ? 0 : mOldItems.size();
    }

    @Override
    public int getNewListSize() {
        return mNewItems == null ? 0 : mNewItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).hashCode() == mNewItems.get(newItemPosition)
                .hashCode();
    }
}
