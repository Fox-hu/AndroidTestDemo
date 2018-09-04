package com.umeng.soexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.umeng.soexample.share.ShareListener;
import com.umeng.soexample.share.ShareManager;
import com.umeng.soexample.share.ShareType;

import java.util.List;

/**
 * Created by wangfei on 16/11/9.
 */
public class ShareAdapter extends BaseAdapter {
    private static final String TARGET_URL = "http://c.y.qq.com/v8/playsong.html?songid=109325260&songmid=000kuo2H2xJqfA&songtype=0&source=mqq&_wv=1";
    private List<PlatForm> list;
    private Context mContext;
    private Activity mActivity;
    private ProgressDialog dialog;

    private String title;
    private String targetUrl;
    private String imgUrl;
    private String imgLocalUrl;
    private String summary;
    private String audioUrl;
    private String appName;

    public ShareAdapter(Context context, List<PlatForm> list) {
        this.list = list;
        this.mContext = context.getApplicationContext();
        this.mActivity = (Activity) context;
        dialog = new ProgressDialog(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.app_shareadapter_share,
                    null);
        }

        ((TextView) convertView.findViewById(R.id.name)).setText(list.get(position).getShowWord());
        convertView.findViewById(R.id.share_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareManager.getDefault().shareTo(list.get(position), ShareType.TEXT, mActivity,
                        createBundle(ShareType.TEXT), shareListener);
            }
        });
        convertView.findViewById(R.id.share_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareManager.getDefault().shareTo(list.get(position), ShareType.IMAGE, mActivity,
                        createBundle(ShareType.IMAGE), shareListener);
            }
        });
        convertView.findViewById(R.id.share_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareManager.getDefault().shareTo(list.get(position), ShareType.VIDEO, mActivity,
                        createBundle(ShareType.VIDEO), shareListener);
            }
        });
        convertView.findViewById(R.id.share_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareManager.getDefault().shareTo(list.get(position), ShareType.APP, mActivity,
                        createBundle(ShareType.APP), shareListener);
            }
        });

        return convertView;
    }

    private ShareListener shareListener = new ShareListener() {
        @Override
        public void onStart(PlatForm platForm) {
            dialog.show();
        }

        @Override
        public void onComplete(Object response) {
            dialog.dismiss();
            Toast.makeText(mContext, "成功了,信息为 = " + response.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(String errorMsg) {
            dialog.dismiss();
            Toast.makeText(mContext, "失败：" + errorMsg, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            dialog.dismiss();
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    public Bundle createBundle(ShareType type) {
        final Bundle params = new Bundle();
        int shareType;
        switch (type) {
            case TEXT:
                shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
                title = mContext.getString(R.string.qqshare_title_content);
                targetUrl = mContext.getString(R.string.qqshare_targetUrl_content);
                imgUrl = mContext.getString(R.string.qqshare_imageUrl_content);
                summary = mContext.getString(R.string.qqshare_summary_content);
                appName = mContext.getString(R.string.qqshare_appName_content);
                break;
            case IMAGE:
                shareType = QQShare.SHARE_TO_QQ_TYPE_IMAGE;
                imgLocalUrl = mContext.getString(R.string.qqshare_local_img);
                break;
            case VIDEO:
                shareType = QQShare.SHARE_TO_QQ_TYPE_AUDIO;
                title = mContext.getString(R.string.qqshare_audio_title);
                targetUrl = TARGET_URL;
                imgUrl = mContext.getString(R.string.qqshare_audio_imgurl);
                summary = mContext.getString(R.string.qqshare_audio_summary);
                appName = mContext.getString(R.string.qqshare_audio_appname);
                audioUrl = mContext.getString(R.string.qqshare_audioUrl);
                break;
            case APP:
                shareType = QQShare.SHARE_TO_QQ_TYPE_APP;
                title = mContext.getString(R.string.qqshare_app_title);
                targetUrl = mContext.getString(R.string.qqshare_app_targeturl);
                imgUrl = mContext.getString(R.string.qqshare_app_imgurl);
                summary = mContext.getString(R.string.qqshare_app_summary);
                break;
            default:
                shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
                break;
        }
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);

        if (type != ShareType.IMAGE) {
            params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
        } else {
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgLocalUrl);
        }

        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        if (type == ShareType.VIDEO) {
            params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, audioUrl);
        }

        return params;
    }
}

