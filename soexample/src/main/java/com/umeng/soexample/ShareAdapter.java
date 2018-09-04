package com.umeng.soexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.share.ShareListener;
import com.umeng.soexample.share.ShareManager;
import com.umeng.soexample.share.ShareParamsHelper;
import com.umeng.soexample.share.ShareType;

import java.util.List;

/**
 * @author wangfei
 * @date 16/11/9
 */
public class ShareAdapter extends BaseAdapter {
    private List<PlatForm> list;
    private Context mContext;
    private Activity mActivity;
    private ProgressDialog dialog;

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

    @SuppressWarnings("unchecked")
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
                ShareParamsHelper paramsHelper = ShareManager.getDefault().getParamsHelper(
                        list.get(position));
                ShareManager.getDefault().shareTo(list.get(position), ShareType.TEXT, mActivity,
                        paramsHelper.createParams(ShareType.TEXT, getBean(position)),
                        shareListener);
            }
        });
        convertView.findViewById(R.id.share_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareParamsHelper paramsHelper = ShareManager.getDefault().getParamsHelper(
                        list.get(position));
                ShareManager.getDefault().shareTo(list.get(position), ShareType.IMAGE, mActivity,
                        paramsHelper.createParams(ShareType.IMAGE, getImg(position)),
                        shareListener);
            }
        });
        convertView.findViewById(R.id.share_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareParamsHelper paramsHelper = ShareManager.getDefault().getParamsHelper(
                        list.get(position));
                ShareManager.getDefault().shareTo(list.get(position), ShareType.VIDEO, mActivity,
                        paramsHelper.createParams(ShareType.VIDEO, getAudio(position)),
                        shareListener);
            }
        });
        convertView.findViewById(R.id.share_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareParamsHelper paramsHelper = ShareManager.getDefault().getParamsHelper(
                        list.get(position));
                ShareManager.getDefault().shareTo(list.get(position), ShareType.APP, mActivity,
                        paramsHelper.createParams(ShareType.APP, getApp(position)), shareListener);
            }
        });

        return convertView;
    }

    private Object getApp(int position) {
        final Object appBean;
        switch (position) {
            case 0:
                appBean = QQtest.getAPP(mContext);
                break;
            case 1:
                appBean = SinaTest.getMulti();
                break;
            default:
                appBean = QQtest.getAPP(mContext);
                break;
        }
        return appBean;
    }

    private Object getAudio(int position) {
        final Object audioBean;
        switch (position) {
            case 0:
                audioBean = QQtest.getAudio(mContext);
                break;
            case 1:
                audioBean = SinaTest.getvideo(mContext);
                break;
            default:
                audioBean = QQtest.getAudio(mContext);
                break;
        }
        return audioBean;
    }

    private Object getImg(int position) {
        final Object imgBean;
        switch (position) {
            case 0:
                imgBean = QQtest.getImg(mContext);
                break;
            case 1:
                imgBean = SinaTest.getImg();
                break;
            default:
                imgBean = QQtest.getImg(mContext);
                break;
        }
        return imgBean;
    }

    private Object getBean(int position) {
        final Object textBean;
        switch (position) {
            case 0:
                textBean = QQtest.getText(mContext);
                break;
            case 1:
                textBean = SinaTest.getText();
                break;
            default:
                textBean = QQtest.getText(mContext);
                break;
        }
        return textBean;
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
}

