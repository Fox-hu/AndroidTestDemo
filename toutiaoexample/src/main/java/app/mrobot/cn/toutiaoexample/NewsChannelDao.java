package app.mrobot.cn.toutiaoexample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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
}
