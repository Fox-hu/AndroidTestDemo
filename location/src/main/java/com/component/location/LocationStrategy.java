package com.component.location;


import com.component.location.baidu.BaiduLocator;
import com.component.location.gps.DefaultLocator;

/**
 * 定位的执行策略
 *
 * @author fox.hu
 */
public interface LocationStrategy<T extends Locator> {
    /**
     * 是否使能该定位器，确定使用哪些定位器
     *
     * @param locator 定位器
     * @return true为使能，false为不使能
     * 默认都使能
     */
    default boolean isLocatorEnable(T locator) {
        return true;
    }

    /**
     * 所有使能的定位器的执行方式 是串行还是并行
     * 如果是并行 则getLocatorPriority无作用
     *
     * @return true是串行执行，false是并行执行
     */
    default boolean isSequence() {
        return true;
    }

    /**
     * 确定该定位器的优先级 级高的优先执行
     *
     * @param locator
     * @return 优先级
     * 默认优先使用默认定位
     */
    default int getLocatorPriority(T locator) {
        int priority = 0;
        if (locator instanceof DefaultLocator) {
            priority = 2;
        }
        if (locator instanceof BaiduLocator) {
            priority = 1;
        }
        return priority;
    }

}
