package app.mrobot.cn.toutiaoexample.widget.helper;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by fox on 2018/1/28.
 */

public class BottomNavigationViewHepler {
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field mShiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            mShiftingMode.setAccessible(true);
            mShiftingMode.setBoolean(menuView, false);
            mShiftingMode.setAccessible(true);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView)menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
