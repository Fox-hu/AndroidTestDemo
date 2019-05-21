package com.component.location;

import android.content.Context;

import com.component.location.baidu.BaiduLocator;
import com.component.location.gps.DefaultLocator;
import com.component.location.vender.Vendor;

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
    private boolean init;
    private final static Map<Vendor, Locator> VENDOR_LOCATOR_MAP = new HashMap<>();
    private final static Map<Vendor, AppLocation> VENDOR_LOCATION_MAP = new HashMap<>();
    private LocationObserver observer;

    static {
        VENDOR_LOCATOR_MAP.put(Vendor.DEFAULT, new DefaultLocator());
        VENDOR_LOCATOR_MAP.put(Vendor.BAIDU, new BaiduLocator());
    }

    private LocationStrategy strategy = new LocationStrategy() {};

    private LocationManager() {

    }

    public void init(Context context) {
        if (!init) {
            VENDOR_LOCATOR_MAP.values().forEach(locator -> locator.init(context));
            init = true;
        }
    }

    public static LocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocationStrategy strategy) {
        this.strategy = strategy;
    }

    public void getLocation(LocationObserver observer) {
        this.observer = observer;
        List<Vendor> collect = VENDOR_LOCATOR_MAP.keySet().stream().filter(
                vendor -> strategy.isLocatorEnable(vendor)).sorted(
                Comparator.comparing(vendor -> strategy.getLocatorPriority(vendor))).collect(
                Collectors.toList());
        collect.forEach(vendor -> VENDOR_LOCATOR_MAP.get(vendor).getLocation(this));
    }

    public void stopLocation() {
        VENDOR_LOCATOR_MAP.values().forEach(Locator :: stopLocation);
    }

    @Override
    public void onGetLocation(Vendor vendor, AppLocation location) {
        synchronized (VENDOR_LOCATION_MAP) {
            VENDOR_LOCATION_MAP.put(vendor, location);
            this.observer.onGetLocation(vendor, location);
        }
    }

    private static class Holder {
        static final LocationManager INSTANCE = new LocationManager();
    }
}
