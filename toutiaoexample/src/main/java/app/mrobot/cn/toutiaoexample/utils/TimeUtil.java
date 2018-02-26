package app.mrobot.cn.toutiaoexample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fox on 2018/2/26.
 */

public class TimeUtil {
    public static String getTimeStampAgo(String timeStamp) {
        Long time = Long.valueOf(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        String format = simpleDateFormat.format(time * 1000L);
        Date date = null;
        try {
            date = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeAgo(date);
    }

    private static String timeAgo(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        if (date != null) {
            long agoTimeInMin = (new Date(System.currentTimeMillis()).getTime() - date.getTime()) /
                                1000 / 60;
            if (agoTimeInMin <= 1) {
                return "刚刚";
            } else if (agoTimeInMin <= 60) {
                //传入的参数时间在６０分钟之内
                return agoTimeInMin + "分钟前";
            } else if (agoTimeInMin <= 60 * 24) {
                return agoTimeInMin / 60 + "小时前";
            } else if (agoTimeInMin <= 60 * 24 * 2) {
                return agoTimeInMin / (60 * 24) + "天前";
            } else {
                return format.format(date);
            }
        } else {
            return format.format(new Date(0));
        }
    }
}
