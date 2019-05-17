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
public class LocationManager implements LocationObserver {
    private static final String TAG = LocationManager.class.getSimpleName();

    private final static Map<Vender, Locator> VENDER_LOCATOR_MAP = new HashMap<>();
    private final static Map<Vender, AppLocation> VENDER_LOCATION_MAP = new HashMap<>();
    private LocationObserver observer;

    static {
        VENDER_LOCATOR_MAP.put(Vender.DEFAULT, new DefaultLocator());
        VENDER_LOCATOR_MAP.put(Vender.BAIDU, new BaiduLocator());
    }

    private LocationStrategy strategy = new LocationStrategy() {};

    private LocationManager() {

    }

    public void init(Context context) {
        VENDER_LOCATOR_MAP.values().forEach(locator -> locator.init(context));
    }

    public static LocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocationStrategy strategy) {
        this.strategy = strategy;
    }

    public void getLocation(LocationObserver observer) {
        this.observer = observer;
        List<Vender> collect = VENDER_LOCATOR_MAP.keySet().stream()
                .filter(vender -> strategy.isLocatorEnable(vender))
                .sorted(Comparator.comparing(vender -> strategy.getLocatorPriority(vender))).collect(
                Collectors.toList());
        collect.forEach(vender -> VENDER_LOCATOR_MAP.get(vender).getLocation(this));
    }

    public void stopLocation() {
        VENDER_LOCATOR_MAP.values().forEach(Locator :: stopLocation);
    }

    @Override
    public void onGetLocation(Vender vender, AppLocation location) {
        synchronized (VENDER_LOCATION_MAP) {
            VENDER_LOCATION_MAP.put(vender, location);
            this.observer.onGetLocation(vender, location);
        }
    }

    private static class Holder {
        static final LocationManager INSTANCE = new LocationManager();
    }
}
