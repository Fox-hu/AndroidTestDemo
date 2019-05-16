package com.component.location;

import android.content.Context;

import com.component.location.baidu.BaiduLocator;
import com.component.location.gps.DefaultLocator;
import com.component.location.vender.Vender;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 定位控制器
 * todo 将异步的调用变为同步的调用
 */
public class SyncLocationManager implements LocationObserver {
    private static final String TAG = SyncLocationManager.class.getSimpleName();

    private final static Map<Vender, Locator> VENDER_LOCATOR_MAP = new HashMap<>();
    private final static Map<Vender, AppLocation> VENDER_LOCATION_MAP = new HashMap<>();
    private LocationObserver observer;

    static {
        VENDER_LOCATOR_MAP.put(Vender.DEFAULT, new DefaultLocator());
        VENDER_LOCATOR_MAP.put(Vender.BAIDU, new BaiduLocator());
    }

    private LocationStrategy strategy = new LocationStrategy() {};

    private SyncLocationManager() {

    }

    public static void init(Context context) {
        VENDER_LOCATOR_MAP.values().forEach(locator -> locator.init(context));
    }

    public static SyncLocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocationStrategy strategy) {
        this.strategy = strategy;
    }

    public void getLocation(LocationObserver observer) {
        this.observer = observer;
        List<Locator> collect = VENDER_LOCATOR_MAP.values().stream().filter(
                locator -> strategy.isLocatorEnable(locator)).sorted(
                Comparator.comparing(locator -> strategy.getLocatorPriority(locator))).collect(
                Collectors.toList());
        collect.forEach(locator -> locator.getLocation(this));
    }

    public void stopLocation() {
        VENDER_LOCATOR_MAP.values().forEach(Locator :: stopLocation);
    }

    @Override
    public void onLocationChanged(Vender vender, AppLocation location) {
        synchronized (VENDER_LOCATION_MAP) {
            VENDER_LOCATION_MAP.put(vender, location);
        }
    }

    private static class Holder {
        static final SyncLocationManager INSTANCE = new SyncLocationManager();
    }
}
