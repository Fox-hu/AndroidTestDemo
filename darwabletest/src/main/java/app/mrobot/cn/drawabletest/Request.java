package app.mrobot.cn.drawabletest;

import android.os.Handler;
import android.os.Looper;


public class Request extends Thread {
    private static final int PAGE_SIZE = 6;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        int size = PAGE_SIZE;
        if (mPage == 1) {
            if (mFirstPageNoMore) {
                size = 1;
            }
            mFirstPageNoMore = !mFirstPageNoMore;
        } else if (mPage == 4) {
            size = 1;
        }

        final int dataSize = size;
        mHandler.post(() -> mCallBack.success(DataServer.getSampleData(dataSize)));
    }
}
