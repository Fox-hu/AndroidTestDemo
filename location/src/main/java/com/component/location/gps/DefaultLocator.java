package com.component.location.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.component.location.AppLocation;
import com.component.location.LocationObserver;
import com.component.location.Locator;

import java.util.List;

/**
 * 系统默认的locator
 * gps，wifi定位
 * @author fox.hu
 */
public class DefaultLocator implements Locator {
    private static final String TAG = DefaultLocator.class.getSimpleName();
    private LocationManager locationManager;
    private List<String> providersNames;
    private String provider;
    private LocationObserver observer;

    @Override
    public void init(Context context, LocationObserver observer) {
        if (context == null) {
            throw new NullPointerException("DefaultLocator context null");
        }
        try {
            locationManager = (LocationManager) context.getApplicationContext().getSystemService(
                    Context.LOCATION_SERVICE);
            configProvider();
            if (observer != null) {
                this.observer = observer;
            }
        } catch (Throwable e) {
            Log.e(TAG, "locationManager init failed");
        }
    }

    private void configProvider() {
        if (locationManager != null) {
            providersNames = locationManager.getProviders(true);
            if (providersNames.contains(LocationManager.GPS_PROVIDER)) {
                provider = LocationManager.GPS_PROVIDER;
            } else if (providersNames.contains(LocationManager.NETWORK_PROVIDER)) {
                provider = LocationManager.NETWORK_PROVIDER;
            }
        }
    }

    @Override
    public void removeObserver(LocationObserver observer) {

    }


    @Override
    public AppLocation getLocation() {
        @SuppressLint("MissingPermission") Location appLocation = locationManager
                .getLastKnownLocation(provider);

        return null;
    }
}
