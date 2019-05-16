package com.component.location;

import com.component.location.vender.Vender;

/**
 * 定位回调
 * @author fox.hu
 */
public interface LocationObserver {

    /**
     * @param vender 哪一个定位器
     * @param location 具体地址信息
     */
    void onLocationChanged(Vender vender, AppLocation location);
}
