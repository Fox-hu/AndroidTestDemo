package app.mrobot.cn.toutiaoexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2018/1/26.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "toutiao";
    private static final int DB_VERSION = 1;
    private static final String CLEAR_TABLE_DATA = "delete_from ";
    private static SQLiteDatabase sSQLiteDatabase;

    private DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

    private static class DatabaseHelperHolder {
        private static final DataBaseHelper sInstance = new DataBaseHelper(InitApp.sContext,
                DB_NAME, null, DB_VERSION);
    }

    public static DataBaseHelper get() {
        return DatabaseHelperHolder.sInstance;
    }

    public static synchronized SQLiteDatabase getDatabase() {
        if (sSQLiteDatabase == null) {
            sSQLiteDatabase = get().getWritableDatabase();
        }
        return sSQLiteDatabase;
    }

    public static void closeDatabase() {
        if (sSQLiteDatabase != null) {
            sSQLiteDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsChannelTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
