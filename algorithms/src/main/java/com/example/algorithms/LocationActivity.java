package com.example.algorithms;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.component.location.AppLocation;
import com.component.location.LocationObserver;
import com.component.location.LocationSyncStrategy;
import com.component.location.SyncLocationManager;
import com.component.location.vender.Vendor;

import java.util.concurrent.ExecutionException;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class LocationActivity extends AppCompatActivity implements LocationObserver {
    AppLocation location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction(
                        "Action", null).show();
            }
        });
        SyncLocationManager.get().init(this);
        SyncLocationManager.get().setStrategy(new LocationSyncStrategy() {
            @Override
            public boolean isLocatorEnable(Vendor vendor) {
                return vendor == Vendor.BAIDU;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PERMISSION_GRANTED) {// 没有权限，申请权限。
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            try {
                location = SyncLocationManager.get().getLocation();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("LocationActivity", "location = " + location);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            location = SyncLocationManager.get().getLocation();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("LocationActivity", "location = " + location);
    }

    @Override
    public void onGetLocation(Vendor vendor, AppLocation location) {
        Log.e("LocationActivity", "location = " + location);
    }
}
