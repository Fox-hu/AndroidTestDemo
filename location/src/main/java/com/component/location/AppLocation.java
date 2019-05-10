package com.component.location;

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

    @Override
    public String toString() {
        return "AppLocation{" + "longitude=" + longitude + ", latitude=" + latitude + ", address='" +
               address + '\'' + '}';
    }
}
