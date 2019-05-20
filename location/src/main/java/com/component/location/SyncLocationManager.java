package com.component.location;

import android.content.Context;
import android.util.Log;

import com.component.location.baidu.BaiduSyncLocator;
import com.component.location.gps.DefaultSyncLocator;
import com.component.location.vender.Vendor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 定位控制器
 * todo 将异步的调用变为同步的调用
 */
public class SyncLocationManager {
    private static final String TAG = SyncLocationManager.class.getSimpleName();

    private final static Map<Vendor, SyncLocator> VENDOR_LOCATOR_MAP = new HashMap<>();
    private LocationObserver observer;

    static {
        VENDOR_LOCATOR_MAP.put(Vendor.DEFAULT, new DefaultSyncLocator());
        VENDOR_LOCATOR_MAP.put(Vendor.BAIDU, new BaiduSyncLocator());
    }

    private LocationSyncStrategy strategy = new LocationSyncStrategy() {};

    private SyncLocationManager() {

    }

    public void init(Context context) {
        VENDOR_LOCATOR_MAP.values().forEach(locator -> locator.init(context));
    }

    public static SyncLocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocationSyncStrategy strategy) {
        this.strategy = strategy;
    }

    public AppLocation getLocation() throws ExecutionException, InterruptedException {
        final CompletableFuture[] completableFutures = getLocationInternal().
                map(future -> future.thenApply(appLocation -> {
                    Log.i(TAG, appLocation.toString());
                    return appLocation;
                })).
                toArray(CompletableFuture[] ::new);
        if (strategy.isAnyOf()) {
            CompletableFuture.anyOf(completableFutures).join();
            return (AppLocation) completableFutures[0].get();
        } else {
            CompletableFuture.allOf(completableFutures).join();
            final List<AppLocation> collect = Arrays.stream(completableFutures).map(
                    completableFuture -> {
                        AppLocation appLocation = new AppLocation(0, 0, Vendor.DEFAULT);
                        try {
                            appLocation = (AppLocation) completableFuture.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        return appLocation;
                    }).filter(AppLocation :: isValid).sorted(Comparator.comparingInt(
                    appLocation -> strategy.getResultPriority(appLocation.getVendor()))).collect(
                    Collectors.toList());
            return collect.get(collect.size() - 1);
        }
    }

    private Stream<CompletableFuture<AppLocation>> getLocationInternal() {
        return VENDOR_LOCATOR_MAP.entrySet().stream().filter(
                vendorSyncLocatorEntry -> strategy.isLocatorEnable(vendorSyncLocatorEntry.getKey()))
                .map(vendorSyncLocatorEntry -> CompletableFuture
                        .supplyAsync(vendorSyncLocatorEntry.getValue() :: getLocation));
    }

    private static class Holder {
        static final SyncLocationManager INSTANCE = new SyncLocationManager();
    }
}
