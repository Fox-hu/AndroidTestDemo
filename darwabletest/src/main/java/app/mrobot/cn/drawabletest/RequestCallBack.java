package app.mrobot.cn.drawabletest;

import java.util.List;

public interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}
