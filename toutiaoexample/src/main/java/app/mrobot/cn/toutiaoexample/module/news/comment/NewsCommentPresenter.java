package app.mrobot.cn.toutiaoexample.module.news.comment;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import app.mrobot.cn.toutiaoexample.api.IMobileNewsApi;
import app.mrobot.cn.toutiaoexample.bean.news.NewsCommentBean;
import app.mrobot.cn.toutiaoexample.utils.RetrofitFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author fox.hu
 * @date 2018/8/14
 */

public class NewsCommentPresenter implements INewsComment.Presenter {
    private static final String TAG = NewsCommentPresenter.class.getSimpleName();
    private INewsComment.View view;
    private String groupId;
    private String itemId;
    private int offset = 0;
    private List<NewsCommentBean.DataBean.CommentBean> commentBeanList = new ArrayList<>();

    public NewsCommentPresenter(INewsComment.View view) {
        this.view = view;
    }

    @Override
    public void doRefresh() {
        commentBeanList.clear();
        offset = 0;
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }


    @Override
    public void doLoadData(String... groupId_ItemId) {
        try {
            if(TextUtils.isEmpty(groupId)){
                groupId = groupId_ItemId[0];
            }
            if(TextUtils.isEmpty(itemId)){
                itemId = groupId_ItemId[1];
            }
        }catch (Exception e){

        }

        RetrofitFactory.getRetrofit().create(IMobileNewsApi.class).getNewsComment(groupId,offset).subscribeOn(
                Schedulers.io()).map(
                (Function<NewsCommentBean, List<NewsCommentBean.DataBean.CommentBean>>) newsCommentBean -> {
                    ArrayList<NewsCommentBean.DataBean.CommentBean> data = new ArrayList<>();
                    for (NewsCommentBean.DataBean dataBean : newsCommentBean.getData()) {
                        data.add(dataBean.getComment());
                    }
                    return data;
                }).observeOn(AndroidSchedulers.mainThread()).as(view.bindAutoDispose()).subscribe(
                commentBeanList -> {
                    if (commentBeanList != null && !commentBeanList.isEmpty()) {
                        doSetAdapter(commentBeanList);
                    } else {
                        doShowNoMore();
                    }
                }, throwable -> doShowNetError());
    }

    @Override
    public void doLoadMore() {
        offset += 20;
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<NewsCommentBean.DataBean.CommentBean> list) {
        commentBeanList.addAll(list);
        view.onSetAdapter(commentBeanList);
        view.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }
}
