package com.umeng.soexample.share.weixin;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fox.hu on 2018/9/7.
 */

public class WeixinShareBean implements Parcelable {
    private String text;
    private String imgPath;
    private String mp3Url;
    private String mediaDescr;
    private String mediaTitle;
    private int thumbImgId;
    private String videoUrl;

    public int getThumbImgId() {
        return thumbImgId;
    }

    public String getMediaDescr() {
        return mediaDescr;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getText() {
        return text;
    }

    public WeixinShareBean(Builder builder) {
        this.text = builder.text;
        this.imgPath = builder.imgPath;
        this.mp3Url = builder.mp3Url;
        this.mediaDescr = builder.mediaDescr;
        this.mediaTitle = builder.mediaTitle;
        this.thumbImgId = builder.thumbImgId;
        this.videoUrl = builder.videoUrl;
    }

    public WeixinShareBean() {}

    public static class Builder {
        private String text;
        private String imgPath;
        private String mp3Url;
        private String mediaDescr;
        private String mediaTitle;
        private int thumbImgId;
        private String videoUrl;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder imgPath(String imgPath) {
            this.imgPath = imgPath;
            return this;
        }

        public Builder mp3Url(String mp3Url) {
            this.mp3Url = mp3Url;
            return this;
        }

        public Builder mediaDescr(String mediaDescr) {
            this.mediaDescr = mediaDescr;
            return this;
        }

        public Builder mediaTitle(String mediaTitle) {
            this.mediaTitle = mediaTitle;
            return this;
        }

        public Builder videoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
            return this;
        }

        public Builder thumbImgId(int thumbImgId) {
            this.thumbImgId = thumbImgId;
            return this;
        }

        public WeixinShareBean bulid() {
            return new WeixinShareBean(this);
        }
    }

    protected WeixinShareBean(Parcel in) {
        this.text = in.readString();
        this.imgPath = in.readString();
        this.mp3Url = in.readString();
        this.mediaDescr = in.readString();
        this.mediaTitle = in.readString();
        this.thumbImgId = in.readInt();
        this.videoUrl = in.readString();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.imgPath);
        dest.writeString(this.mp3Url);
        dest.writeString(this.mediaDescr);
        dest.writeString(this.mediaTitle);
        dest.writeInt(this.thumbImgId);
        dest.writeString(this.videoUrl);
    }

    public static final Creator<WeixinShareBean> CREATOR = new Creator<WeixinShareBean>() {
        @Override
        public WeixinShareBean createFromParcel(Parcel source) {return new WeixinShareBean(source);}

        @Override
        public WeixinShareBean[] newArray(int size) {return new WeixinShareBean[size];}
    };
}
