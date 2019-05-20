package com.component.location;

import com.component.location.vender.Vendor;

/**
 * location的具体定义 包括经度纬度地区值
 * todo 地区code
 *
 * @author fox.hu
 */
public class AppLocation {
    private double longitude = 0;
    private double latitude = 0;
    private String address;
    private Vendor vendor;

    public AppLocation(double longitude, double latitude, Vendor vendor) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.vendor = vendor;
    }

    public static boolean isValid(AppLocation target) {
        return target.getLatitude() != 0 && target.getLongitude() != 0 ;
    }

    @Override
    public String toString() {
        return "AppLocation{" + "longitude=" + longitude + ", latitude=" + latitude +
               ", address='" + address + '\'' + ", vendor=" + vendor + '}';
    }


    public AppLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Vendor getVendor() {
        return vendor;
    }
}
