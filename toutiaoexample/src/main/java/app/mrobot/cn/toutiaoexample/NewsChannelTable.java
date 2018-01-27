package app.mrobot.cn.toutiaoexample;

/**
 * Created by fox on 2018/1/27.
 */

public class NewsChannelTable {
    /**
     * 新闻频道信息表
     */
    public static final String TABLE_NAME = "NewsChannelTable";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IS_ENABLE = "isEnable";
    public static final String POSITION = "position";


    public static final String CREATE_TABLE =
            "create table if not exists " + TABLE_NAME + "(" + ID + " text primary key, " + NAME +
            "text, " + IS_ENABLE + " text default '1', " + POSITION + " text) ";
}
