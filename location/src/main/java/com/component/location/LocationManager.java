package com.component.location;

public class LocationManager {
    private static final String TAG = LocationManager.class.getSimpleName();

    private LocationStrategy strategy;
    private LocationManager() {}

    public static LocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocationStrategy strategy) {
        this.strategy = strategy;
    }

    private static class Holder {
        static final LocationManager INSTANCE = new LocationManager();
    }


}
