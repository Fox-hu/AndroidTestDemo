package com.umeng.soexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangfei on 16/11/9.
 */
public class ShareAdapter extends BaseAdapter implements View.OnClickListener {
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

            }
        });
        convertView.findViewById(R.id.share_img).setOnClickListener(this);
        convertView.findViewById(R.id.share_video).setOnClickListener(this);
        convertView.findViewById(R.id.share_app).setOnClickListener(this);

        if (position == list.size() - 1) {
            convertView.findViewById(R.id.divider).setVisibility(View.GONE);
        } else {
            convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }
        return convertView;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_text:
                break;
            case R.id.share_img:
                break;
            case R.id.share_video:
                break;
            case R.id.share_app:
                break;
            default:
                break;
        }
    }
}

