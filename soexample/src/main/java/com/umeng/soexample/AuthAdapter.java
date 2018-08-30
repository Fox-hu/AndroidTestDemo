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

import com.umeng.soexample.thirdLogin.AuthLoginManager;
import com.umeng.soexample.thirdLogin.PlatForm;
import com.umeng.soexample.thirdLogin.PlatFormInfo;
import com.umeng.soexample.thirdLogin.onAuthListener;

import java.util.List;

/**
 * Created by wangfei on 16/11/9.
 */
public class AuthAdapter extends BaseAdapter {
    private List<PlatForm> list;
    private Context mContext;
    private Activity mActivity;
    private ProgressDialog dialog;

    public AuthAdapter(Context context, List<PlatForm> list) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.app_authadapter_share,
                    null);
        }
        //        final boolean isauth = UMShareAPI.get(mContext).isAuthorize(mActivity,
        //                list.get(position));

        ((TextView) convertView.findViewById(R.id.name)).setText(list.get(position).getShowWord());

        TextView authBtn = (TextView) convertView.findViewById(R.id.auth_button);
        //        if (isauth) {
        //            authBtn.setText("删除授权");
        //        } else {
        authBtn.setText("授权");
        //        }
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                if (isauth) {
                //                    UMShareAPI.get(mContext).deleteOauth(mActivity, list.get(position),
                //                            authListener);
                //                } else {
                //                    UMShareAPI.get(mContext).doOauthVerify(mActivity, list.get(position),
                //                            authListener);
                //                }
                AuthLoginManager.getDefault().fetchPlatFormInfo(list.get(position), mActivity, authListener);
            }
        });
        if (position == list.size() - 1) {
            convertView.findViewById(R.id.divider).setVisibility(View.GONE);
        } else {
            convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    onAuthListener authListener = new onAuthListener() {
        @Override
        public void onStart(PlatForm platform) {
            dialog.show();
        }

        @Override
        public void onComplete(PlatFormInfo platformInfo) {
            dialog.dismiss();
            Toast.makeText(mContext, "成功了,信息为 = " + platformInfo.toString(), Toast.LENGTH_LONG)
                    .show();
            notifyDataSetChanged();
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
