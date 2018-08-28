package app.mrobot.cn.toutiaoexample.module.video;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.LoadingBean;
import app.mrobot.cn.toutiaoexample.bean.LoadingEndBean;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.BaseActivity;
import app.mrobot.cn.toutiaoexample.module.news.comment.INewsComment;
import app.mrobot.cn.toutiaoexample.utils.DiffCallback;
import app.mrobot.cn.toutiaoexample.utils.ImageLoader;
import app.mrobot.cn.toutiaoexample.utils.OnLoadMoreListener;
import app.mrobot.cn.toutiaoexample.widget.Register;
import app.mrobot.cn.toutiaoexample.widget.helper.FullScreenVideoPlayer;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author fox.hu
 * @date 2018/8/27
 */

public class VideoContentActivity extends BaseActivity implements IVideoContent.View {
    private static final String TAG = VideoContentActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;
    private ContentLoadingProgressBar progressBar;
    private FullScreenVideoPlayer player;
    private SwipeRefreshLayout swipeRefreshLayout;

    private IVideoContent.Presenter presenter;
    private MultiNewsArticleDataBean dataBean;
    private String groupId;
    private String itemId;
    private String videoId;
    private String videoTitle;
    private Items oldItems = new Items();
    private boolean canLoadMore = false;
    private int currentAction;

    public static void launch(MultiNewsArticleDataBean bean) {
        InitApp.sContext.startActivity(new Intent(InitApp.sContext, VideoContentActivity.class)
                .putExtra(TAG, bean).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_content);
        presenter = new VideoContentPresenter(this);
        initView();
        initData();
        onLoadData();
    }

    private void initData() {
        Intent intent = getIntent();
        try {
            dataBean = intent.getParcelableExtra(TAG);
            if (null != dataBean.getVideo_detail_info()) {
                if (null != dataBean.getVideo_detail_info().getDetail_video_large_image()) {
                    String imgUrl = dataBean.getVideo_detail_info().getDetail_video_large_image()
                            .getUrl();
                    if (!TextUtils.isEmpty(imgUrl)) {
                        ImageLoader.loadCenterCrop(this, imgUrl, player.thumbImageView,
                                R.color.viewBackground, R.mipmap.error_image);
                    }
                }
            }
            this.groupId = dataBean.getGroup_id() + "";
            this.itemId = dataBean.getItem_id() + "";
            this.videoId = dataBean.getVideo_id();
            this.videoTitle = dataBean.getTitle();
            oldItems.add(dataBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MultiTypeAdapter(oldItems);
        Register.registerVideoContentItem(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMore();
                }
            }
        });

        progressBar = findViewById(R.id.pb_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        progressBar.show();

        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
            onLoadData();
        });

        player = findViewById(R.id.jz_video);
        player.setonClickFullScreenListener(
                () -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));

        player.thumbImageView.setOnTouchListener((v, event) -> false);

    }

    @Override
    public void onShowLoading() {
        progressBar.show();
    }

    @Override
    public void onHideLoading() {
        progressBar.hide();
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void onShowNetError() {
        Snackbar.make(recyclerView, R.string.network_error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(INewsComment.Presenter presenter) {

    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items();
        newItems.add(dataBean);
        newItems.addAll(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(newItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    public void onShowNoMore() {
        runOnUiThread(() -> {
            if (oldItems.size() > 1) {
                Items newItems = new Items(oldItems);
                //删除原列表中的loadingbean
                newItems.remove(newItems.size() - 1);
                newItems.add(new LoadingEndBean());
                adapter.setItems(newItems);
                adapter.notifyDataSetChanged();
            } else if (oldItems.size() == 0) {
                oldItems.add(new LoadingEndBean());
                adapter.setItems(oldItems);
                adapter.notifyDataSetChanged();
            }
            canLoadMore = false;
        });
    }

    @Override
    public void onSetVideoPlay(String url) {
        player.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoTitle);

        JZVideoPlayer.setJzUserAction((type, url1, screen, objects) -> {
            if (type == JZUserActionStandard.ON_CLICK_START_THUMB ||
                type == JZUserAction.ON_CLICK_START_ICON ||
                type == JZUserAction.ON_CLICK_RESUME ||
                type == JZUserAction.ON_CLICK_START_AUTO_COMPLETE) {
            }

            if (type == JZUserAction.ON_CLICK_PAUSE || type == JZUserAction.ON_AUTO_COMPLETE) {
            }

            if (type == JZUserAction.ON_ENTER_FULLSCREEN) {
                currentAction = JZUserAction.ON_ENTER_FULLSCREEN;

                View decorView = getWindow().getDecorView();
                int uiOptions = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                } else {
                    uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                }
                decorView.setSystemUiVisibility(uiOptions);

            }

            if (type == JZUserAction.ON_QUIT_FULLSCREEN) {
                currentAction = JZUserAction.ON_QUIT_FULLSCREEN;

                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(0);

            }
        });

    }

    @Override
    public void onLoadData() {
        presenter.doLoadData(groupId, itemId);
        presenter.doLoadVideoData(videoId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.setJzUserAction(null);
        JZVideoPlayer.releaseAllVideos();
        FullScreenVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
