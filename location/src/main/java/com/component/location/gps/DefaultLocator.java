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
import com.component.location.Locator;
import com.component.location.vender.Vender;

/**
 * 系统默认的locator
 * gps，wifi定位
 *
 * @author fox.hu
 */
public class DefaultLocator implements Locator, LocationListener {
    private static final String TAG = DefaultLocator.class.getSimpleName();
    private LocationManager locationManager;
    private LocationObserver observer;
    private LocationProvider gpsProvider;
    private LocationProvider netProvider;

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

    private void configProvider() {
        if (locationManager != null) {
            //1.通过GPS定位，较精确。也比較耗电
            gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
            //2.通过网络定位。对定位精度度不高或省点情况可考虑
            netProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
        }
    }

    @Override
    public void removeObserver(LocationObserver observer) {
        locationManager.removeUpdates(this);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void getLocation(LocationObserver observer) {
        if (observer != null) {
            this.observer = observer;
        }

        if (gpsProvider != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        } else if (netProvider != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
        }
    }

    @Override
    public void stopLocation() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        if (observer != null) {
            observer.onLocationChanged(Vender.DEFAULT, new AppLocation(longitude, latitude));
        }
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
