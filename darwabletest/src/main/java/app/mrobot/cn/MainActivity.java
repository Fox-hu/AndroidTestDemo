package app.mrobot.cn;

import com.component.common.BaseListActivity;

import app.mrobot.cn.custom.CanvasPaintActivity;
import app.mrobot.cn.drawabletest.RvbgActivity;
import app.mrobot.cn.drawabletest.VideoViewBackgroundActivity;
import app.mrobot.cn.shapetest.ShapeMainActivity;

public class MainActivity extends BaseListActivity {

    @Override
    protected void initItem() {
        activityMap.put("ShapeMainActivity", ShapeMainActivity.class);
        activityMap.put("VideoViewBackgroundActivity", VideoViewBackgroundActivity.class);
        activityMap.put("RvbgActivity", RvbgActivity.class);
        activityMap.put("CanvasPaintActivity", CanvasPaintActivity.class);
    }
}
