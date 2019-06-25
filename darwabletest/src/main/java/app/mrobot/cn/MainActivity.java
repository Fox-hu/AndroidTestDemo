package app.mrobot.cn;

import com.component.common.BaseListActivity;

import app.mrobot.cn.custom.CanvasPaintActivity;
import app.mrobot.cn.drawabletest.ExtraDemoActivity;
import app.mrobot.cn.drawabletest.FlexboxActivity;
import app.mrobot.cn.drawabletest.IvbgActivity;
import app.mrobot.cn.drawabletest.RvbgActivity;
import app.mrobot.cn.drawabletest.VideoViewBackgroundActivity;
import app.mrobot.cn.shapetest.ShapeMainActivity;

public class MainActivity extends BaseListActivity {

    @Override
    protected void initItem() {
        activityMap.put("ShapeMainActivity", ShapeMainActivity.class);
        activityMap.put("VideoViewBackgroundActivity", VideoViewBackgroundActivity.class);
        activityMap.put("RvbgActivity", RvbgActivity.class);
        activityMap.put("IvbgActivity", IvbgActivity.class);
        activityMap.put("CanvasPaintActivity", CanvasPaintActivity.class);
        activityMap.put("FlexboxActivity", FlexboxActivity.class);
        activityMap.put("ExtraDemoActivity", ExtraDemoActivity.class);
    }
}
