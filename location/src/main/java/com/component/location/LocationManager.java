package com.component.location;

public class LocationManager {
    private static final String TAG = LocationManager.class.getSimpleName();

    private LocatStrategy strategy;
    private LocationManager() {}

    public static LocationManager get() {
        return Holder.INSTANCE;
    }

    public void setStrategy(LocatStrategy strategy) {
        this.strategy = strategy;
    }

    private static class Holder {
        static final LocationManager INSTANCE = new LocationManager();
    }
}
