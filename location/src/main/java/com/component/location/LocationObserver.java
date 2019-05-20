package com.component.location;

import com.component.location.vender.Vendor;

/**
 * 定位回调
 * @author fox.hu
 */
public interface LocationObserver {

    /**
     * @param vendor 哪一个定位器
     * @param location 具体地址信息
     */
    void onGetLocation(Vendor vendor, AppLocation location);
}
