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
import com.component.location.LocationManager;
import com.component.location.LocationObserver;
import com.component.location.LocationStrategy;
import com.component.location.vender.Vender;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class LocationActivity extends AppCompatActivity implements LocationObserver {
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
        LocationManager.get().init(this);
        LocationManager.get().setStrategy(new LocationStrategy() {
            @Override
            public boolean isLocatorEnable(Vender vender) {
                return vender == Vender.DEFAULT;
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
            LocationManager.get().getLocation(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LocationManager.get().getLocation(this);
    }

    @Override
    public void onGetLocation(Vender vender, AppLocation location) {
        Log.e("LocationActivity", "location = " + location);
    }
}
