package app.mrobot.cn.toutiaoexample.bean;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by fox on 2018/1/27.
 */

public class NewsChannelBean implements Comparable<NewsChannelBean> {
    private String channelId;
    private String channelName;
    private int isEnable;
    private int position;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        NewsChannelBean bean = (NewsChannelBean) obj;

        if (isEnable != bean.isEnable) {
            return false;
        }
        if (position != bean.position) {
            return false;
        }
        if (TextUtils.isEmpty(channelId) ? !TextUtils.isEmpty(bean.channelId) : !channelId.equals(
                bean.getChannelId())) {
            return false;
        }
        if (TextUtils.isEmpty(channelName)
            ? !TextUtils.isEmpty(bean.channelName)
            : !channelName.equals(bean.getChannelName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = TextUtils.isEmpty(channelId) ? 0 : channelId.hashCode();
        result = 31 * result + (TextUtils.isEmpty(channelName) ? 0 : channelName.hashCode());
        result = 31 * result + isEnable;
        result = 31 * result + position;
        return result;
    }

    @Override
    public int compareTo(@NonNull NewsChannelBean o) {
        return this.position - o.getPosition();
    }
}
