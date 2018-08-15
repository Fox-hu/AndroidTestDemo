package app.mrobot.cn.toutiaoexample.module.news.content;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.MultiNewsArticleDataBean;
import app.mrobot.cn.toutiaoexample.module.BaseFragment;
import app.mrobot.cn.toutiaoexample.module.news.comment.NewsCommentActivity;
import app.mrobot.cn.toutiaoexample.utils.ImageLoader;
import app.mrobot.cn.toutiaoexample.widget.helper.AppBarStateChangeListener;

/**
 * Created by fox.hu on 2018/8/10.
 */

public class NewsContentFragment extends BaseFragment<INewsContent.Presenter> implements
        INewsContent.View {
    private static final String TAG = NewsContentFragment.class.getSimpleName();
    private static final String IMG = "img";

    private MultiNewsArticleDataBean bean;
    private Toolbar toolbar;
    private WebView webView;
    private NestedScrollView scrollView;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private ContentLoadingProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String imgUrl;
    private boolean hasImg;
    private Bundle mBundle;
    private String shareUrl;
    private String shareTitle;
    private String mediaName;
    private String mediaId;
    private String mediaUrl;

    public static NewsContentFragment get(Parcelable dataBean, String imgUrl) {
        NewsContentFragment instance = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, dataBean);
        bundle.putString(IMG, imgUrl);
        instance.setArguments(bundle);
        return instance;
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
        Snackbar.make(scrollView, R.string.network_error, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void setPresenter(INewsContent.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsContentPresenter(this);
        }
    }

    @Override
    protected int attachLayoutId() {
        mBundle = getArguments();
        if (mBundle != null) {
            imgUrl = mBundle.getString(IMG);
        }
        hasImg = !TextUtils.isEmpty(imgUrl);
        return hasImg ? R.layout.news_content_with_img : R.layout.news_content_without_img;
    }

    @Override
    protected void initData() {
        if (mBundle == null) {
            return;
        }
        try {
            bean = mBundle.getParcelable(TAG);
            presenter.doLoadData(bean);
            shareUrl = !TextUtils.isEmpty(bean.getShare_url())
                       ? bean.getShare_url()
                       : bean.getDisplay_url();
            shareTitle = bean.getTitle();
            mediaName = bean.getMedia_name();
            mediaId = bean.getMedia_info().getMedia_id();
            mediaUrl = "http://toutiao.com/m" + mediaId;
        } catch (Exception e) {

        }
        if (hasImg) {
            ImageLoader.loadCenterCrop(getActivity(), mBundle.getString(IMG), imageView,
                    R.mipmap.error_image, R.mipmap.error_image);
            appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                @Override
                protected void onStateChanged(AppBarLayout appBarLayout, State state) {
                    Window window = null;
                    if (getActivity() != null && getActivity().getWindow() != null) {
                        window = getActivity().getWindow();
                    }
                    if (state == State.EXPANDED) {
                        collapsingToolbarLayout.setTitle("");
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && window != null) {
                            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        }
                    } else if (state == State.COLLAPSED) {
                        //do nothing
                    } else {
                        collapsingToolbarLayout.setTitle(mediaName);
                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && window != null) {
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        }
                    }
                }
            });
        } else {
            toolbar.setTitle(mediaName);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(hasImg){
            appBarLayout.setExpanded(false);
        }
    }

    @Override
    protected void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        initToolbar(toolbar, true, "");

        scrollView = view.findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> onHideLoading());

        webView = view.findViewById(R.id.webView);
        initWebClient();

        progressBar = view.findViewById(R.id.pb_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        progressBar.show();

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(() -> swipeRefreshLayout.post(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.doLoadData(bean);
        }));
        if (hasImg) {
            appBarLayout = view.findViewById(R.id.appbar);
            collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
            imageView = view.findViewById(R.id.iv_image);
        }
        setHasOptionsMenu(true);
    }

    private void initWebClient() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setBlockNetworkImage(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url)) {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 90) {
                    onHideLoading();
                } else {
                    onShowLoading();
                }
            }
        });
    }


    @Override
    public void onLoadWebView(String url, boolean flag) {
        if (flag) {
            webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_browser, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_comment:
                NewsCommentActivity.launch(bean.getGroup_id() + "", bean.getItem_id() + "");
                break;
            case R.id.action_open_media_home:
                break;
            case R.id.action_share:
                break;
            case R.id.action_open_in_browser:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
