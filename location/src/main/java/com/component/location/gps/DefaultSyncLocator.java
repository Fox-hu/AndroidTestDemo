package com.component.location.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.component.location.AppLocation;
import com.component.location.SyncLocator;

import java.util.List;

/**
 * 系统默认的locator
 * gps，wifi定位
 *
 * @author fox.hu
 */
public class DefaultSyncLocator implements SyncLocator {
    private static final String TAG = DefaultSyncLocator.class.getSimpleName();
    private LocationManager locationManager;

    @Override
    public void init(Context context) {
        if (context == null) {
            throw new NullPointerException("DefaultLocator context null");
        }
        try {
            locationManager = (LocationManager) context.getApplicationContext().getSystemService(
                    Context.LOCATION_SERVICE);
        } catch (Throwable e) {
            Log.e(TAG, "locationManager init failed");
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public AppLocation getLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }

        if (bestLocation != null) {
            return new AppLocation(bestLocation.getLongitude(), bestLocation.getLatitude());
        } else {
            return new AppLocation(0, 0);
        }
    }
}
