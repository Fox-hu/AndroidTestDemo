package app.mrobot.cn.toutiaoexample.module;

import java.util.List;

/**
 * Created by fox on 2018/2/24.
 */

public interface IBaseListView<T> extends IBaseView<T>{
    /**
     * 设置适配器
     * @param list
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
