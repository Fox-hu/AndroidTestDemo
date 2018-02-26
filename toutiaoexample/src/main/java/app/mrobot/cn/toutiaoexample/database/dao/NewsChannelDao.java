package app.mrobot.cn.toutiaoexample.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.mrobot.cn.toutiaoexample.Constants;
import app.mrobot.cn.toutiaoexample.InitApp;
import app.mrobot.cn.toutiaoexample.database.table.NewsChannelTable;
import app.mrobot.cn.toutiaoexample.R;
import app.mrobot.cn.toutiaoexample.bean.news.NewsChannelBean;
import app.mrobot.cn.toutiaoexample.database.DataBaseHelper;

/**
 * Created by admin on 2018/1/26.
 */

public class NewsChannelDao {
    private static final String TAG = NewsChannelDao.class.getSimpleName();
    private static final int DEFAULT_INIT_NEWS_NUM = 8;
    private SQLiteDatabase db;

    public NewsChannelDao() {
        this.db = DataBaseHelper.getDatabase();
    }

    public void initNewsData() {
        String[] newsIds = InitApp.sContext.getResources().getStringArray(R.array.mobile_news_id);
        String[] newsNames = InitApp.sContext.getResources().getStringArray(
                R.array.mobile_news_name);
        for (int i = 0; i < DEFAULT_INIT_NEWS_NUM; i++) {
            add(newsIds[i], newsNames[i], Constants.NEWS_CHANNEL_ENABLE, i);
        }
    }

    private boolean add(String newsId, String newsName, int newsChannelEnable, int position) {
        ContentValues values = new ContentValues();
        values.put(NewsChannelTable.ID, newsId);
        values.put(NewsChannelTable.NAME, newsName);
        values.put(NewsChannelTable.IS_ENABLE, newsChannelEnable);
        values.put(NewsChannelTable.POSITION, position);
        long result = db.insert(NewsChannelTable.TABLE_NAME, null, values);
        return result != -1;
    }

    public List<NewsChannelBean> query(int isEnable) {
        Cursor cursor = db.query(NewsChannelTable.TABLE_NAME, null,
                NewsChannelTable.IS_ENABLE + "=?", new String[]{isEnable + ""}, null, null, null);
        List<NewsChannelBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setChannelId(cursor.getString(NewsChannelTable.ID_ID));
            bean.setChannelName(cursor.getString(NewsChannelTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(NewsChannelTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(NewsChannelTable.ID_POSITION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }
}
