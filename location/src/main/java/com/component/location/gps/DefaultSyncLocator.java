package com.component.location.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

import com.component.location.AppLocation;
import com.component.location.LocationObserver;
import com.component.location.SyncLocator;

import java.util.concurrent.CountDownLatch;

/**
 * 系统默认的locator
 * gps，wifi定位
 *
 * @author fox.hu
 */
public class DefaultSyncLocator implements SyncLocator, LocationListener {
    private static final String TAG = DefaultSyncLocator.class.getSimpleName();
    private LocationManager locationManager;
    private LocationProvider gpsProvider;
    private LocationProvider netProvider;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private AppLocation result;

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
        configProvider();
    }

    @SuppressLint("MissingPermission")
    @Override
    public AppLocation getLocation() {

            if (gpsProvider != null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
            } else if (netProvider != null) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1,
                        this);
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;

    }

    private void configProvider() {
        if (locationManager != null) {
            //1.通过GPS定位，较精确。也比較耗电
            gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
            //2.通过网络定位。对定位精度度不高或省点情况可考虑
            netProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
        }
    }

    public void removeObserver(LocationObserver observer) {
        locationManager.removeUpdates(this);
    }


    @Override
    public void stopLocation() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        result = new AppLocation(longitude, latitude);
        countDownLatch.countDown();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
