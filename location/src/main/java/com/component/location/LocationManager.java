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
 * todo 串行与并行的回调的方式还可以细化 目前所有的locator都使用同一个回调，待优化
 */
public class LocationManager {
    private static final String TAG = LocationManager.class.getSimpleName();

    private final static Map<Vender, Locator> sLocatorMap = new HashMap<>();

    static {
        sLocatorMap.put(Vender.DEFAULT, new DefaultLocator());
        sLocatorMap.put(Vender.BAIDU, new BaiduLocator());
    }

    private LocationStrategy strategy;

    private LocationManager() {

    }

    public static void init(Context context) {
        sLocatorMap.values().forEach(locator -> locator.init(context));
    }

    public static LocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocationStrategy strategy) {
        this.strategy = strategy;
    }

    public void getLocation(LocationObserver observer) {
        if (strategy.isSequence()) {
            List<Locator> collect = sLocatorMap.values().stream().filter(
                    locator -> strategy.isLocatorEnable(locator)).sorted(
                    Comparator.comparing(locator -> strategy.getLocatorPriority(locator))).collect(
                    Collectors.toList());
            collect.forEach(locator -> locator.getLocation(observer));
        } else {
            sLocatorMap.values().forEach(locator -> locator.getLocation(observer));
        }
    }

    public void stopLocation() {
        sLocatorMap.values().forEach(Locator :: stopLocation);
    }

    private static class Holder {
        static final LocationManager INSTANCE = new LocationManager();
    }
}
