package com.component.location;


import android.content.Context;

/**
 * 所有定位器的抽象
 * 所有定位实现类都要实现该接口，不能直接调用原来接口
 *
 * @author fox.hu
 */
public interface Locator {

    void init(Context context);

    void getLocation(LocationObserver observer);

    void stopLocation();

    void removeObserver(LocationObserver observer);
}
