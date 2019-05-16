package com.component.location.baidu;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.component.location.AppLocation;
import com.component.location.LocationObserver;
import com.component.location.SyncLocator;

import java.util.concurrent.CountDownLatch;

/**
 * http://lbsyun.baidu.com/index.php?title=android-locsdk
 * 百度定位获取经纬度逻辑
 *
 * @author fox.hu
 */
public class BaiduSyncLocator extends BDAbstractLocationListener implements SyncLocator {
    private static final String TAG = BaiduSyncLocator.class.getSimpleName();

    private LocationClient locationClient;
    private AppLocation result;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void init(Context context) {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        locationClient = new LocationClient(context.getApplicationContext());
        //声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        //注册监听函数
        locationClient.registerLocationListener(this);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
    }

    @Override
    public AppLocation getLocation() {
        locationClient.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        result = new AppLocation(longitude, latitude);
        countDownLatch.countDown();
    }


    @Override
    public void stopLocation() {
        locationClient.stop();
    }

    public void removeObserver(LocationObserver observer) {
        locationClient.unRegisterLocationListener(this);
    }
}
